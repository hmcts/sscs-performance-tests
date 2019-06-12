package uk.gov.hmcts.reform.sscs.performance.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmcts.reform.sscs.performance.trackyourappeal
//import uk.gov.hmcts.reform.sscs.performance.processes._
import uk.gov.hmcts.reform.sscs.performance.trackyourappeal.TrackYourAppeal
import uk.gov.hmcts.reform.sscs.performance.utils.Environment
//import uk.gov.hmcts.reform.sscs.performance.simulations.lifecycle.SimulationHooks
//import uk.gov.hmcts.reform.idam.{User,LoginPage}

object  CreateTYASimulation {

  val WaitForNextIteration = Environment.waitForNextIteration
  val feeder = csv("TYA_Details.csv").circular

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(Environment.sscsTYAURL)
    .headers(Environment.commonHeader)


  implicit val postHeaders: Map[String, String] = Map(
    "Origin" -> Environment.sscsTYAURL
  )

  def createTYAScenario =
    exec(
      feed(feeder),
      TrackYourAppeal.home,        //  LoginPage.logIn(testUsers.head),
      TrackYourAppeal.enterSurname,
      TrackYourAppeal.aboutHearing,
      TrackYourAppeal.evidence
    )

  pace(WaitForNextIteration)

  /*setUp(createClaimScenario
    .inject(rampUsers(1).over(10 seconds))
    .protocols(httpProtocol))
    .maxDuration(90 minutes)
    .assertions(
      global.responseTime.max.lt(5000),
      forAll.failedRequests.count.lt(20)
    )*/

}
