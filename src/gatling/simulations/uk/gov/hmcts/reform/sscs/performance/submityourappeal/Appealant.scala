package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
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
      .post("/have-you-got-an-mrn")
      .formParam("haveAMRN", "yes")
     // .formParam(csrfParameter, csrfTemplate)
     // .check(CsrfCheck.save)
   //   .check(regex("When is your Mandatory Reconsideration Notice (MRN) dated?"))
      )
          .pause(thinktime)

val DWPIssuingOffice=
  exec(http("TX10_SSCS_DWPIssuingOffice")
    .post("/dwp-issuing-office")
    .formParam("pipNumber", "2")
     // .formParam(csrfParameter, csrfTemplate)
   //.check(regex("Are you an 'appointee'?"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)


  val MRNDate=
  exec(http("TX11_SSCS_MRNDate")
    .post("/mrn-date")
    .formParam("mrnDate.day", mrnDate)
    .formParam("mrnDate.month", mrnMonth)
    .formParam("mrnDate.year", mrnYear)
   // .formParam(csrfParameter, csrfTemplate)

  //  .check(regex("Find DWP's address on the top right of your Mandatory Reconsideration Notice (MRN)"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)

  val name=
  exec(http("TX12_SSCS_Appealant_Name")
    .post("/enter-appellant-name")
    .formParam("title", "Mr.")
    .formParam("firstName", "${firstname}")
    .formParam("lastName", "${lastname}")
   // .formParam(csrfParameter, csrfTemplate)
   // .check(regex("Enter your date of birth"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)

  val DOB=
  exec(http("TX13_SSCS_Appealant_DOB")
    .post("/enter-appellant-dob")
    .formParam("date.day", "01")
    .formParam("date.month", "08")
    .formParam("date.year", "1990")
   // .formParam(csrfParameter, csrfTemplate)
    .check(regex("Enter your National Insurance number"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)

  val niNumber=
  exec(http("TX14_SSCS_Appealant_NiNumber")
    .post("/enter-appellant-nino")
    .formParam("nino", "${ninumber}")
   // .formParam(csrfParameter, csrfTemplate)
  //  .check(regex("Enter your contact details"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)


  val contactDetailslookup=
    exec(http("TX15_SSCS_Appealant_ContactDetailsLookup")
      .post("/enter-appellant-contact-details")
  .formParam("submitType", "lookup")
    .formParam("postcodeLookup", "tw33sd")
    .formParam("phoneNumber", "")
    .formParam("emailAddress", "")
      .check(status.in(200,302)))

  val contactDetailsSelection=
    exec(http("TX15_SSCS_Appealant_ContactDetailsSelection")
      .post("/enter-appellant-contact-details")
      .formParam("submitType", "addressSelection")
      .formParam("postcodeLookup", "tw33sd")
      .formParam("postcodeAddress", "100021551083")
      .formParam("phoneNumber", "")
      .formParam("emailAddress", "")
      .check(status.in(200,302)))


  val contactDetails=
  exec(http("TX15_SSCS_Appealant_ContactDetails")
    .post("/enter-appellant-contact-details")
    .formParam("submitType", "")
    .formParam("postcodeLookup", "tw33sd")
    .formParam("postcodeAddress", "100021551083")
    .formParam("addressLine1", "12, HIBERNIA GARDENS")
    .formParam("addressLine2", "")
    .formParam("townCity", "HOUNSLOW")
    .formParam("county", "HOUNSLOW")
    .formParam("postCode", "TW3 3SD")
    .formParam("phoneNumber", "07424523427")
    .formParam("emailAddress", "${email}@mailinator.com")
  //  .formParam(csrfParameter, csrfTemplate)
    .check(regex("Do you want to receive text message notifications?"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)


  val textRemainders=
  exec(http("TX16_SSCS_TextRemainders")
    .post("/appellant-text-reminders")
    .formParam("doYouWantTextMsgReminders", "no")
   // .formParam(csrfParameter, csrfTemplate)
   // .check(regex("Do you want to receive text message notifications?"))

   // .check(CsrfCheck.save))
  )
    .pause(thinktime)

    /*.exec(http("TX17_SSCS_Mobile")
      .post("/enter-mobile")
      .formParam("enterMobile", "${textremainder}"))
    .pause(thinktime)*/

    /*.exec(http("request_313")
      .post("/sms-confirmation")
    .header("Content-Length","0"))*/








}
