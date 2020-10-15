package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import uk.gov.hmcts.reform.sscs.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object LandingPage {

  val thinktime = Environment.thinkTime

 // val feeder = csv("sscs_details.csv").circular
   
  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {
  //1

  // =======================================================================================
  // Have a look at the landing page
  // =======================================================================================

  val landingPage =   exec(http("TX01_SSCS_LandingPage")
      .get("/")
   .check(status.is(200))
      .check(regex("Appeal a benefit decision")))
      .pause(thinktime)

  // =======================================================================================
  // Have a look at the 'before you appeal' page
  // =======================================================================================


  val beforeYouAppeal=
     exec(http("TX02_SSCS_BeforeYouAppeal")
       .get("/before-you-appeal")
       .check(status.is(200))
       .check(regex("Before you appeal")))
     .pause(thinktime)

  // =======================================================================================
  // Have a look at the 'help with your appeal' page
  // =======================================================================================

  val helpWithAppeal=
    exec(http("TX03_SSCS_HelpWithYourAppeal")
      .get("/help-with-appeal")
      .check(status.is(200))
      .check(regex("Help with your appeal")))
      .pause(thinktime)

  // =======================================================================================
  // Have a look at the 'start an appeal' page
  // =======================================================================================

  val startAnAppeal=
    exec(http("TX04_SSCS_StartAnAppeal")
      .get("/start-an-appeal")
      .check(status.is(200))
      .check(regex("Start an appeal")))
      .pause(thinktime)



}
