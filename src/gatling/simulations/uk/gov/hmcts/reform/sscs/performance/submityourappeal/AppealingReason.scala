package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import uk.gov.hmcts.reform.sscs.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object AppealingReason{

  val thinktime = Environment.thinkTime
  val firstName = Environment.firstName
  val lastName = Environment.lastName
  val sscsSYAURL = Environment.sscsSYAURL

  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {

  val headers_check_appeal = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-US,en;q=0.9",
    "cache-control" -> "max-age=0",
    "origin" -> sscsSYAURL,
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1")


  // =======================================================================================
  // Provide a reason for appealing
  // =======================================================================================

 val reasonForAppealing=

   exec(http("TX27_SSCS_ReasonForAppealGet")
     .get("/reason-for-appealing")
   )
       .pause(thinktime)
    .exec(http("TX28_SSCS_ReasonForAppealPost")
    .post("/reason-for-appealing/item-0")
    .formParam("item.whatYouDisagreeWith", "Disagree - I am Performance Testing")
    .formParam("item.reasonForAppealing", "Reason For Appeal - I am Performance Testing")
  //  .formParam(csrfParameter, csrfTemplate)
      .check(status.in(200,302))
      // .check(regex("Your reasons for appealing"))
  //  .check(CsrfCheck.save)
  )
    .pause(thinktime)

  // =======================================================================================
  // Provide other reasons for appealing
  // =======================================================================================

  val finalReasonForAppealing=
    exec(http("TX29_SSCS_FinalReasonForAppeal")
      .post("/reason-for-appealing")
      .formParam("item.whatYouDisagreeWith-0", "Disagree - I am Performance Testing")
      .formParam("item.reasonForAppealing-0", "Reason For Appeal - I am Performance Testing")
     // .formParam(csrfParameter, csrfTemplate)
      .check(status.in(200,302))
      // .check(regex("Your reasons for appealing"))
    //  .check(CsrfCheck.save)
    )
      .pause(thinktime)

   /* .exec(http("request_398")
      .post("/reason-for-appealing"))
    .pause(thinktime)*/

    .exec(http("TX30_SSCS_OtherReasonForAppealing")
      .post("/other-reason-for-appealing")
      .formParam("otherReasonForAppealing", "Other Reason - I am Performance Testing")
   // .formParam(csrfParameter, csrfTemplate)
      .check(status.in(200,302))
      // .check(CsrfCheck.save)

  )
    .pause(thinktime)

  //from here go to upload evidence

  // =======================================================================================
  // Provide evidence
  // =======================================================================================

  val evidence=
  exec(http("TX31_SSCS_ProvideEvidence")
    .post("/evidence-provide")
    .formParam("evidenceProvide", "no")
    .check(status.in(200,302))
    //.check(CsrfCheck.save)
  )
    .pause(thinktime)

  // =======================================================================================
  // Indicate whether you plan to attend the hearing
  // =======================================================================================


val attendHearing=
  exec(http("TX32_SSCS_AttendHearing")
    .post("/the-hearing")
    .formParam("attendHearing", "yes")
   // .formParam(csrfParameter, csrfTemplate)
    .check(status.in(200,302))
    //.check(regex("Select the suitable options for you to attend the hearing."))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)

  // =======================================================================================
  // Indicate how you want to attend the hearing
  // =======================================================================================


  val hearingOptions=
    exec(http("TX33_SSCS_HearingOptions")
      .post("/hearing-options")
      .formParam("selectOptions.telephone.phoneNumber", "")
      .formParam("selectOptions.video.email", "")
      .formParam("selectOptions.faceToFace.requested", "true")
      // .formParam(csrfParameter, csrfTemplate)
      .check(status.in(200,302))
      //.check(regex("Select the suitable options for you to attend the hearing."))
      // .check(CsrfCheck.save)
    )
      .pause(thinktime)

  // =======================================================================================
  // Indicate whether you need support at the hearing
  // =======================================================================================


  val supportHearing=
  exec(http("TX34_SSCS_HearingSupport")
    .post("/hearing-support")
    .formParam("arrangements", "no")
   // .formParam(csrfParameter, csrfTemplate)
    .check(status.in(200,302))
    //.check(regex("Your availability for a hearing"))
    //.check(CsrfCheck.save)
  )
    .pause(thinktime)

    // =======================================================================================
    // Provide your availability for a hearing
    // =======================================================================================

    val hearingAvailability =
    exec(http("TX35_SSCS_HearingAvailability")
      .post("/hearing-availability")
      .formParam("scheduleHearing", "no")
     // .formParam(csrfParameter, csrfTemplate)
      .check(status.in(200,302))
      //.check(regex("Check your answers"))
     // .check(CsrfCheck.save)
    )
    .pause(thinktime)


  // =======================================================================================
  // Check the appeal
  // =======================================================================================

  val checkYourAppeal2=
  exec(http("TX36_SSCS_CheckYourAppeal")
  .post("/check-your-appeal")
    .formParam("signer",firstName+" "+lastName )
      .headers(headers_check_appeal)
      .check(status.in(200,302 ))
   // .formParam(csrfParameter, csrfTemplate)
    .check(regex("Appeal submitted"))
  )
    .pause(thinktime)














}
