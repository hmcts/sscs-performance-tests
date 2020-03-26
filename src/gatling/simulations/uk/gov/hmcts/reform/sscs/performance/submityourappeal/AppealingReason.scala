package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import uk.gov.hmcts.reform.sscs.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object AppealingReason{

  val thinktime = Environment.thinkTime
  

  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {


  val headers_check_appeal = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-US,en;q=0.9",
    "cache-control" -> "max-age=0",
    "origin" -> "https://benefit-appeal.aat.platform.hmcts.net",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1")




 val reasonForAppealing=

   exec(http("request_806")
     .get("/reason-for-appealing")
   )
       .pause(5)
    .exec(http("TX20_SSCS_ReasonForAppeal")
    .post("/reason-for-appealing/item-0")
    .formParam("item.whatYouDisagreeWith", "Disagree - I am Performance Testing")
    .formParam("item.reasonForAppealing", "Reason For Appeal - I am Performance Testing")
  //  .formParam(csrfParameter, csrfTemplate)
   // .check(regex("Your reasons for appealing"))
  //  .check(CsrfCheck.save)
  )
    .pause(thinktime)

  val finalReasonForAppealing=
    exec(http("TX20_SSCS_FinalReasonForAppeal")
      .post("/reason-for-appealing")
      .formParam("item.whatYouDisagreeWith-0", "Disagree - I am Performance Testing")
      .formParam("item.reasonForAppealing-0", "Reason For Appeal - I am Performance Testing")
     // .formParam(csrfParameter, csrfTemplate)
     // .check(regex("Your reasons for appealing"))
    //  .check(CsrfCheck.save)
    )
      .pause(thinktime)

   /* .exec(http("request_398")
      .post("/reason-for-appealing"))
    .pause(thinktime)*/

    .exec(http("TX21_SSCS_OtherReasonForAppealing")
      .post("/other-reason-for-appealing")
      .formParam("otherReasonForAppealing", "Other Reason - I am Performance Testing")
   // .formParam(csrfParameter, csrfTemplate)

   // .check(CsrfCheck.save)

  )
    .pause(thinktime)

  //from here go to upload evidence
  val evidence=
  exec(http("request_432")
    .post("/evidence-provide")
    .formParam("evidenceProvide", "no")
      //.check(CsrfCheck.save)
  )
    .pause(thinktime)


val attendHearing=
  exec(http("TX32_SSCS_AttendHearing")
    .post("/the-hearing")
    .formParam("attendHearing", "yes")
   // .formParam(csrfParameter, csrfTemplate)
    .check(regex("Do you need any support at the hearing?"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)


  val supportHearing=
  exec(http("TX33_SSCS_HearingSupport")
    .post("/hearing-support")
    .formParam("arrangements", "no")
   // .formParam(csrfParameter, csrfTemplate)
    .check(regex("Your availability for a hearing"))
    //.check(CsrfCheck.save)
  )
    .pause(thinktime)

    .exec(http("TX34_SSCS_HearingAvailability")
      .post("/hearing-availability")
      .formParam("scheduleHearing", "no")
     // .formParam(csrfParameter, csrfTemplate)
    .check(regex("Check your answers"))
     // .check(CsrfCheck.save)
    )
    .pause(thinktime)
val checkYourAppeal=
  exec(http("TX35_SSCS_CheckYourAppeal")
  .post("/check-your-appeal")
    .formParam("signer", "vijayrama")
      .headers(headers_check_appeal)
      .check(status.in(200,302 ))
   // .formParam(csrfParameter, csrfTemplate)
    .check(regex("Your appeal has been submitted"))

  )
    .pause(thinktime)














}