package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.sscs.performance.simulations.checks.CsrfCheck
import uk.gov.hmcts.reform.sscs.performance.simulations.checks.CsrfCheck.{csrfParameter, csrfTemplate}
import uk.gov.hmcts.reform.sscs.performance.submityourappeal.StartAppealPage.thinktime
//import uk.gov.hmcts.reform.sscs.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object Appealant{

  val thinktime = Environment.thinkTime
  val mrnDate=Environment.mrnDate
  val mrnMonth=Environment.mrnMonth
  val mrnYear=Environment.mrnYear
  



   
  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {
  val haveMRN=
    exec(http("TX09_SSCS_HaveMRN")
      .post("/have-a-mrn")
      .formParam("haveAMRN", "yes")
      .formParam(csrfParameter, csrfTemplate)
      .check(CsrfCheck.save)
    )
    //  .check(regex("address on the top right of your Mandatory Reconsideration Notice (MRN)")))
      .pause(thinktime)


val DWPIssuingOffice=
  exec(http("TX10_SSCS_DWPIssuingOffice")
    .post("/dwp-issuing-office")
    .formParam("pipNumber", "1")
      .formParam(csrfParameter, csrfTemplate)
   // .check(regex("When is your Mandatory Reconsideration Notice"))
    .check(CsrfCheck.save)
  )
    .pause(thinktime)


  val MRNDate=
  exec(http("TX11_SSCS_MRNDate")
    .post("/mrn-date")
    .formParam("mrnDate.day", mrnDate)
    .formParam("mrnDate.month", mrnMonth)
    .formParam("mrnDate.year", mrnYear)
    .formParam(csrfParameter, csrfTemplate)

    .check(regex("Enter your name"))
    .check(CsrfCheck.save)
  )
    .pause(thinktime)

  val name=
  exec(http("TX12_SSCS_Appealant_Name")
    .post("/enter-appellant-name")
    .formParam("title", "mr")
    .formParam("firstName", "${firstname}")
    .formParam("lastName", "${lastname}")
    .formParam(csrfParameter, csrfTemplate)
    .check(regex("Enter your date of birth"))
    .check(CsrfCheck.save)
  )
    .pause(thinktime)

  val DOB=
  exec(http("TX13_SSCS_Appealant_DOB")
    .post("/enter-appellant-dob")
    .formParam("date.day", "10")
    .formParam("date.month", "11")
    .formParam("date.year", "1982")
    .formParam(csrfParameter, csrfTemplate)
    .check(regex("Enter your National Insurance number"))
    .check(CsrfCheck.save)
  )
    .pause(thinktime)

  val niNumber=
  exec(http("TX14_SSCS_Appealant_NiNumber")
    .post("/enter-appellant-nino")
    .formParam("nino", "${ninumber}")
    .formParam(csrfParameter, csrfTemplate)
    .check(regex("Enter your contact details"))
    .check(CsrfCheck.save)
  )
    .pause(thinktime)

  val contactDetails=
  exec(http("TX15_SSCS_Appealant_ContactDetails")
    .post("/enter-appellant-contact-details")
    .formParam("addressLine1", "Performance Street")
    .formParam("addressLine2", "")
    .formParam("townCity", "PerfCity")
    .formParam("county", "PerfCounty")
    .formParam("postCode", "WV95LT")
    .formParam("phoneNumber", "07540612047")
    .formParam("emailAddress", "${email}"+"@perftest.uk.gov")
    .formParam(csrfParameter, csrfTemplate)
    .check(regex("Do you want to receive text message notifications?"))
    .check(CsrfCheck.save)
  )
    .pause(thinktime)


  val textRemainders=
  exec(http("TX16_SSCS_TextRemainders")
    .post("/appellant-text-reminders")
    .formParam("doYouWantTextMsgReminders", "no")
    .formParam(csrfParameter, csrfTemplate)

    .check(CsrfCheck.save))
    .pause(thinktime)

    /*.exec(http("TX17_SSCS_Mobile")
      .post("/enter-mobile")
      .formParam("enterMobile", "${textremainder}"))
    .pause(thinktime)*/

    /*.exec(http("request_313")
      .post("/sms-confirmation")
    .header("Content-Length","0"))*/








}
