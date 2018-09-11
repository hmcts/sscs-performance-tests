package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import uk.gov.hmcts.reform.cmc.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object AppealingReason{

  val thinktime = Environment.thinkTime
  
    val feeder = csv("TYA_Details.csv").circular
   
  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {


  val headers_check_appeal = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
    "Cache-Control" -> "max-age=0",
    "Connection" -> "keep-alive",
    "Origin" -> "https://sscs-tribunals-frontend-sprod.service.core-compute-sprod.internal",
    "Upgrade-Insecure-Requests" -> "1")

 val reasonForAppealing=
  exec(http("TX20_SSCS_ReasonForAppeal")
    .post("/reason-for-appealing/item-0")
    .formParam("item.whatYouDisagreeWith", "Disagree - I am Performance Testing")
    .formParam("item.reasonForAppealing", "Reason For Appeal - I am Performance Testing")
    .check(regex("Your reasons for appealing")))
    .pause(thinktime)

   /* .exec(http("request_398")
      .post("/reason-for-appealing"))
    .pause(thinktime)*/

    .exec(http("TX21_SSCS_OtherReasonForAppealing")
      .post("/other-reason-for-appealing")
      .formParam("otherReasonForAppealing", "Other Reason - I am Performance Testing"))
    .pause(thinktime)

  //from here go to upload evidence
 /* val evidence=
  exec(http("request_432")
    .post("/sending-evidence"))
    .pause(thinktime)*/

val attendHearing=
  exec(http("TX32_SSCS_AttendHearing")
    .post("/the-hearing")
    .formParam("attendHearing", "yes")
    .check(regex("Do you need any support at the hearing?")))
    .pause(thinktime)


  val supportHearing=
  exec(http("TX33_SSCS_HearingSupport")
    .post("/hearing-support")
    .formParam("arrangements", "no")
    .check(regex("Your availability for a hearing")))
    .pause(thinktime)

    .exec(http("TX34_SSCS_HearingAvailability")
      .post("/hearing-availability")
      .formParam("scheduleHearing", "no")
    .check(regex("Check your answers")))
    .pause(thinktime)

val checkYourAppeal=
  exec(http("TX35_SSCS_CheckYourAppeal")
  .post("/check-your-appeal")
      .headers(headers_check_appeal)
    .formParam("signer", "${signer}")
    .check(regex("Your appeal has been submitted")))
    .pause(thinktime)














}
