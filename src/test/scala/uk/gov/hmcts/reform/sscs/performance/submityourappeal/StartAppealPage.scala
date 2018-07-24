package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import uk.gov.hmcts.reform.cmc.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object StartAppealPage{

  val thinktime = Environment.thinkTime

  val feeder = csv("sscs_details.csv").circular
  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {
 val entry =   exec(http("TX05_SSCS_Entry")
      .get("/entry"))
      .pause(thinktime)

  val benifitType =
  exec(http("TX06_SSCS_BenifitType")
    .post("/benefit-type")
    .formParam("benefitType", "Personal Independence Payment (PIP)")
    .check(regex("Enter your postcode")))
    .pause(thinktime)

  val postCodeCheck =
  exec(http("TX07_SSCS_PostCodeCheck")
    .post("/postcode-check")
    .formParam("postcode", "WV95LT")
    .check(regex("You’re an appointee if you’ve been appointed")))

    .pause(thinktime)

val areYouAnAppointee =
  exec(http("TX08_SSCS_AreYouAnAppointee")
    .post("/are-you-an-appointee")
    .formParam("isAppointee", "no")
    .check(regex("Your appeal will be decided by an independent tribunal")))
    .pause(thinktime)

  val independance=
  exec(http("request_141")
    .post("/independence"))
    .pause(thinktime)






}
