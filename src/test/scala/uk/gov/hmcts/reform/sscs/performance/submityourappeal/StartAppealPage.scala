package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.cmc.performance.simulations.checks.CsrfCheck.{csrfParameter, csrfTemplate}
import uk.gov.hmcts.reform.cmc.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.cmc.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object StartAppealPage{

  val thinktime = Environment.thinkTime

  val feeder = csv("sscs_details.csv").circular
  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {
 val entry =   exec(http("TX05_SSCS_Entry")
      .get("/")
   .check(CsrfCheck.save))
      .pause(thinktime)
    .feed(feeder)

  val benifitType =
  exec(http("TX06_SSCS_BenifitType")
    .post("/benefit-type")
    .formParam("benefitType", "Personal Independence Payment (PIP)")
    .formParam(csrfParameter, csrfTemplate)
    .check(regex("Enter your postcode"))
    .check(CsrfCheck.save)
  )
    .pause(thinktime)

  val postCodeCheck =
  exec(http("TX07_SSCS_PostCodeCheck")
    .post("/postcode-check")
    .formParam("postcode", "WV95LT")
    .formParam(csrfParameter, csrfTemplate)
    .check(regex("You’re an appointee if you’ve been appointed"))
    .check(CsrfCheck.save)
  )

    .pause(thinktime)

val areYouAnAppointee =
  exec(http("TX08_SSCS_AreYouAnAppointee")
    .post("/are-you-an-appointee")
    .formParam("isAppointee", "no")
    .formParam(csrfParameter, csrfTemplate)
    .check(regex("Your appeal will be decided by an independent tribunal"))
  //  .check(CsrfCheck.save)
  )
    .pause(thinktime)

  val independance=
  exec(http("request_141")
    .post("/independence")
    .check(CsrfCheck.save)
  )
    .pause(thinktime)






}
