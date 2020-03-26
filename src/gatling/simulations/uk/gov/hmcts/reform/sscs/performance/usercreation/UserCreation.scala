package uk.gov.hmcts.reform.sscs.performance.usercreation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import uk.gov.hmcts.reform.sscs.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._
import uk.gov.service.notify.{NotificationClient, NotificationList}

import scala.collection.JavaConverters.iterableAsScalaIterableConverter
import scala.util.matching.Regex

object UserCreation {

  val thinktime = Environment.thinkTime
  val idamBaseUrl = Environment.idamBaseUrl
  val addIdamUserUrl = idamBaseUrl + "/testing-support/accounts"
  val feeder = jsonFile("idamUsers.json").circular

  val headers_pwd = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Sec-Fetch-Dest" -> "document",
    "Sec-Fetch-Mode" -> "navigate",
    "Sec-Fetch-Site" -> "none",
    "Sec-Fetch-User" -> "?1",
    "Upgrade-Insecure-Requests" -> "1")


   
  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {
  //1


  val createUsers =
    feed(feeder)
      .exec(http("Create IDAM users")
        .post(addIdamUserUrl)
        .body(StringBody(
          """{
          "email": "vijay.ao.admin@mailinator.com",
          "forename": "SSCS-perftest-Fname999",
          "surname": "SSCS-perftest-Lname999",
          "password": "Pass19word",
          "roles": [{"code": "prd-admin"}]
        }"""
        ))
        .asJson
        .headers(Map("Content-Type" -> "application/json"))
        .check(status.is(201)))
      // .check(status.is(201)))
      .pause(10)


 val emailNotification =

  exec {

    session =>
      val client = new NotificationClient("sidam_perftest-b7ab8862-25b4-41c9-8311-cb78815f7d2d-ebb113ff-da17-4646-a39e-f93783a993f4")
      val pattern = new Regex("token.+")
     // val str = findEmail(client,session("generatedEmail").as[String])

      val str = findEmail(client,"perftestsample@mailinator.com")
      //session.set("activationLink", (pattern findFirstMatchIn str.get).mkString)
      session.set("activationLink", (pattern findFirstMatchIn str.get).mkString.trim.replace(")", ""))
  }
    .pause(60)
    .exec(http("SelfReg01_TX03_Password")
      .get("https://idam-web-public.perftest.platform.hmcts.net/users/register?&${activationLink}")
      .headers(headers_pwd)
      .check(status.is(200))
      .check(css("input[name='token']", "value").saveAs("token"))
      .check(css("input[name='code']", "value").saveAs("code"))
      .check(css("input[name='_csrf']", "value").saveAs("_csrf")))
    .pause(60)
    .exec(http("SelfReg01_TX04_Activate").post("https://idam-web-public.perftest.platform.hmcts.net/users/activate")
      .formParam("_csrf", "${_csrf}")
      .formParam("code", "${code}")
      .formParam("token", "${token}")
      .formParam("password1", "Pass19word")
      .formParam("password2", "Pass19word")
      .check(status.is(200)))
  /* .exec {
   session =>
     val fw = new BufferedWriter(new FileWriter("OrgId.csv", true))
     try {
       fw.write(session("orgRefCode").as[String] + "," + session("generatedEmail").as[String] + "\r\n")
     }
     finally fw.close()
     session
 }*/



  def findEmail(client: NotificationClient, emailAddress:String) : Option[String] = {
    var emailBody = findEmailByStatus(client, emailAddress, "created")
    if (emailBody.isDefined) {
      return emailBody
    }
    emailBody = findEmailByStatus(client, emailAddress, "sending")
    if (emailBody.isDefined) {
      return emailBody
    }
    emailBody = findEmailByStatus(client, emailAddress, "delivered")
    if (emailBody.isDefined) {
      return emailBody
    }
    findEmailByStatus(client, emailAddress, "failed")
  }

  def findEmailByStatus(client: NotificationClient, emailAddress: String, status: String) : Option[String] = {
    val notificationList = client.getNotifications(status, "email", null, null)
    println("Searching notifications from " + status)
    val emailBody = getEmailBodyByEmailAddress(notificationList, emailAddress)
    if (emailBody.isDefined) {
      return emailBody
    }
    None
  }

  def getEmailBodyByEmailAddress(notifications: NotificationList, emailAddress: String) : Option[String] = {
    for(notification <- notifications.getNotifications.asScala) {
      if (notification.getEmailAddress.get().equalsIgnoreCase(emailAddress)) {
        println("Found match for email " + emailAddress)
        return Some(notification.getBody)
      } else {
        println("Comparing " + notification.getEmailAddress.get() + " with " + emailAddress)
      }
    }
    None
  }




}
