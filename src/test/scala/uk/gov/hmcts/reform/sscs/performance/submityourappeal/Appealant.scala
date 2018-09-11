package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.sscs.performance.submityourappeal.StartAppealPage.thinktime
//import uk.gov.hmcts.reform.cmc.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object Appealant{

  val thinktime = Environment.thinkTime
  



   
  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {
  val haveMRN=
    exec(http("TX09_SSCS_HaveMRN")
      .post("/have-a-mrn")
      .formParam("haveAMRN", "yes"))
    //  .check(regex("address on the top right of your Mandatory Reconsideration Notice (MRN)")))
      .pause(thinktime)


val DWPIssuingOffice=
  exec(http("TX10_SSCS_DWPIssuingOffice")
    .post("/dwp-issuing-office")
    .formParam("pipNumber", "1")
    .check(regex("When is your Mandatory Reconsideration Notice")))
    .pause(thinktime)

  val MRNDate=
  exec(http("TX11_SSCS_MRNDate")
    .post("/mrn-date")
    .formParam("mrnDate.day", "02")
    .formParam("mrnDate.month", "09")
    .formParam("mrnDate.year", "2018")
    .check(regex("Enter your name")))
    .pause(thinktime)

  val name=
  exec(http("TX12_SSCS_Appealant_Name")
    .post("/enter-appellant-name")
    .formParam("title", "mr")
    .formParam("firstName", "${firstname}")
    .formParam("lastName", "${lastname}")
    .check(regex("Enter your date of birth")))
    .pause(thinktime)

  val DOB=
  exec(http("TX13_SSCS_Appealant_DOB")
    .post("/enter-appellant-dob")
    .formParam("date.day", "10")
    .formParam("date.month", "11")
    .formParam("date.year", "1982")
    .check(regex("Enter your National Insurance number")))
    .pause(thinktime)

  val niNumber=
  exec(http("TX14_SSCS_Appealant_NiNumber")
    .post("/enter-appellant-nino")
    .formParam("nino", "${ninumber}")
    .check(regex("Enter your contact details")))
    .pause(thinktime)

  val contactDetails=
  exec(http("TX15_SSCS_Appealant_ContactDetails")
    .post("/enter-appellant-contact-details")
    .formParam("addressLine1", "Performance Street")
    .formParam("addressLine2", "")
    .formParam("townCity", "PerfCity")
    .formParam("county", "PerfCounty")
    .formParam("postCode", "WV95LT")
    .formParam("phoneNumber", "7540612047")
    .formParam("emailAddress", "${email}"+"@perftest.uk.gov")
    .check(regex("Do you want to receive text message notifications?")))
    .pause(thinktime)


  val textRemainders=
  exec(http("TX16_SSCS_TextRemainders")
    .post("/appellant-text-reminders")
    .formParam("doYouWantTextMsgReminders", "no"))
   // .check(regex("Enter your mobile phone number")))
    .pause(thinktime)

    /*.exec(http("TX17_SSCS_Mobile")
      .post("/enter-mobile")
      .formParam("enterMobile", "${textremainder}"))
    .pause(thinktime)*/

    /*.exec(http("request_313")
      .post("/sms-confirmation")
    .header("Content-Length","0"))*/








}
