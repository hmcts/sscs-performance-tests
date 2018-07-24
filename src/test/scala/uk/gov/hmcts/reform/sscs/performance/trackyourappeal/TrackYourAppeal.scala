package uk.gov.hmcts.reform.sscs.performance.trackyourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import uk.gov.hmcts.reform.cmc.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object TrackYourAppeal {

  val tyaThinkTime = Environment.tyaThinkTime
  
    val feeder = csv("TYA_Details.csv").circular
   
  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {
 val home =   exec(http("TX01_SSCS_TYA_LandingPage")
      .get("/trackyourappeal/${reference}")
      .check(regex("Enter your last name")))
      .pause(tyaThinkTime)

  val enterSurname=  exec(http("TX02_SSCS_TYA_EnterSurname")
        .post("/validate-surname/${reference}/trackyourappeal")
        .formParam("surname", "${surname}")
      .check(status.is(200))
      .check(regex("Your PIP benefit appeal")))

      .pause(tyaThinkTime)

  val aboutHearing=
  exec(http("TX03_SSCS_TYA_AboutHearing")
    .get("/abouthearing/${reference}")
    .check(regex("What to expect at your hearing")))
    .pause(tyaThinkTime)

  val evidence=
  exec(http("TX04_SSCS_TYA_Evidence")
    .get("/evidence/${reference}")
      .check(regex("Providing evidence to support your appeal")))
    .pause(tyaThinkTime)




}
