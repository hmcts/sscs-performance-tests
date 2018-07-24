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

 val reasonForAppealing=
  exec(http("TX20_SSCS_ReasonForAppeal")
    .post("/reason-for-appealing/item-0")
    .formParam("item.whatYouDisagreeWith", "disagree with the appeal sscs perf test")
    .formParam("item.reasonForAppealing", "i want child benifit back sscs perf test")
    .check(regex("Your reasons for appealing")))
    .pause(thinktime)

   /* .exec(http("request_398")
      .post("/reason-for-appealing"))
    .pause(thinktime)*/

    .exec(http("TX21_SSCS_OtherReasonForAppealing")
      .post("/other-reason-for-appealing")
      .formParam("otherReasonForAppealing", "there are few other reasons sscs perf test"))
    .pause(thinktime)

 /* val evidence=
  exec(http("request_432")
    .post("/sending-evidence"))
    .pause(thinktime)*/

val attendHearing=
  exec(http("TX22_SSCS_AttendHearing")
    .post("/the-hearing")
    .formParam("attendHearing", "yes")
    .check(regex("Do you need any support at the hearing?")))
    .pause(thinktime)


  val supportHearing=
  exec(http("TX23_SSCS_HearingSupport")
    .post("/hearing-support")
    .formParam("arrangements", "no")
    .check(regex("Your availability for a hearing")))
    .pause(thinktime)

    .exec(http("TX24_SSCS_HearingAvailability")
      .post("/hearing-availability")
      .formParam("scheduleHearing", "no")
    .check(regex("Check your answers")))
    .pause(thinktime)

val checkYourAppeal=
  exec(http("TX25_SSCS_CheckYourAppeal")
  .post("/check-your-appeal")
    .formParam("signer", "${signer}")
    .check(regex("Your appeal has been submitted")))
    .pause(thinktime)














}
