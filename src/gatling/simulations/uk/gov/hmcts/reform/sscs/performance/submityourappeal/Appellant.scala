package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import uk.gov.hmcts.reform.sscs.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object Appellant{

  val thinktime = Environment.thinkTime
  val firstName = Environment.firstName
  val lastName = Environment.lastName
  val mrnDate=Environment.mrnDate
  val mrnMonth=Environment.mrnMonth
  val mrnYear=Environment.mrnYear


  // =======================================================================================
  // Indicate whether you have an MRN
  // =======================================================================================
   
  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {
  val   haveMRN=
    exec(http("TX09_SSCS_HaveMRN")
      .post("/have-you-got-an-mrn")
      .formParam("haveAMRN", "yes")
     // .formParam(csrfParameter, csrfTemplate)
      .check(status.in(200,302))
      // .check(CsrfCheck.save)
   //   .check(regex("When is your Mandatory Reconsideration Notice (MRN) dated?"))
      )
          .pause(thinktime)

  // =======================================================================================
  // Provide the DWP issuing office
  // =======================================================================================

val DWPIssuingOffice=
  exec(http("TX10_SSCS_DWPIssuingOffice")
    .post("/dwp-issuing-office")
    .formParam("pipNumber", "3")
     // .formParam(csrfParameter, csrfTemplate)
    .check(status.in(200,302))
    //.check(regex("Are you an 'appointee'?"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)


  // =======================================================================================
  // Provide the MRN date
  // =======================================================================================

  val MRNDate=
  exec(http("TX11_SSCS_MRNDate")
    .post("/mrn-date")
    .formParam("mrnDate.day", mrnDate)
    .formParam("mrnDate.month", mrnMonth)
    .formParam("mrnDate.year", mrnYear)
   // .formParam(csrfParameter, csrfTemplate)
      .check(status.in(200,302))
    //  .check(regex("Find DWP's address on the top right of your Mandatory Reconsideration Notice (MRN)"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)

  // =======================================================================================
  // Provide your name
  // =======================================================================================

  val name=
  exec(http("TX12_SSCS_Appellant_Name")
    .post("/enter-appellant-name")
    .formParam("title", "Mr.")
    .formParam("firstName", firstName)
    .formParam("lastName", lastName)
    .check(status.in(200,302))
    // .formParam(csrfParameter, csrfTemplate)
   // .check(regex("Enter your date of birth"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)


  // =======================================================================================
  // Submit your date of birth
  // =======================================================================================

  val DOB=
  exec(http("TX13_SSCS_Appellant_DOB")
    .post("/enter-appellant-dob")
    .formParam("date.day", "01")
    .formParam("date.month", "08")
    .formParam("date.year", "1990")
   // .formParam(csrfParameter, csrfTemplate)
    .check(status.in(200,302))
    //.check(regex("Enter your National Insurance number"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)

  // =======================================================================================
  // Provide your national insurance number
  // =======================================================================================

  val niNumber=
  exec(http("TX14_SSCS_Appellant_NiNumber")
    .post("/enter-appellant-nino")
    .formParam("nino", "${ninumber}")
   // .formParam(csrfParameter, csrfTemplate)
    .check(status.in(200,302))
    //  .check(regex("Enter your contact details"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)


  // =======================================================================================
  // Provide your contact details
  // =======================================================================================

  val contactDetailslookup=
    exec(http("TX15_SSCS_Appellant_ContactDetailsLookup")
      .post("/enter-appellant-contact-details")
      .formParam("submitType", "lookup")
      .formParam("postcodeLookup", "E1 6JJ")
      .formParam("phoneNumber", "")
      .formParam("emailAddress", "")
      .check(status.in(200,302)))

  // =======================================================================================
  // Select an address
  // =======================================================================================

  val contactDetailsSelection=
    exec(http("TX15_SSCS_Appellant_ContactDetailsSelection")
      .post("/enter-appellant-contact-details")
      .formParam("submitType", "addressSelection")
      .formParam("postcodeLookup", "E1 6JJ")
      .formParam("postcodeAddress", "10008297237")
      .formParam("phoneNumber", "")
      .formParam("emailAddress", "")
      .check(status.in(200,302)))


  // =======================================================================================
  // Confirm your contact details
  // =======================================================================================

  val contactDetails=
  exec(http("TX15_SSCS_Appellant_ContactDetails")
         .post("/enter-appellant-contact-details")
         .formParam("submitType", "")
         .formParam("postcodeLookup", "E1 6JJ")
         .formParam("postcodeAddress", "10008297237")
         .formParam("addressLine1", "LYLES")
         .formParam("addressLine2", "56 THE TEA BUILDING, SHOREDITCH HIGH STREET")
         .formParam("townCity", "LONDON")
         .formParam("county", "LONDON")
         .formParam("postCode", "E1 6JJ")
         .formParam("phoneNumber", "")
    .formParam("emailAddress", "${idamUser}")
  //  .formParam(csrfParameter, csrfTemplate)
    .check(status.in(200,302))
    //.check(regex("Do you want to receive text message notifications?"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)


  // =======================================================================================
  // Indicate whether you want to receive reminders by text message
  // =======================================================================================

  val textRemainders=
  exec(http("TX16_SSCS_TextRemainders")
    .post("/appellant-text-reminders")
    .formParam("doYouWantTextMsgReminders", "no")
   // .formParam(csrfParameter, csrfTemplate)
    .check(status.in(200,302))
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
