package uk.gov.hmcts.reform.sscs.performance.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
//import uk.gov.hmcts.reform.sscs.performance.processes._
import uk.gov.hmcts.reform.sscs.performance.trackyourappeal.SSCSMYA
import uk.gov.hmcts.reform.sscs.performance.utils.Environment
//import uk.gov.hmcts.reform.sscs.performance.simulations.lifecycle.SimulationHooks
//import uk.gov.hmcts.reform.idam.{User,LoginPage}

object  CreateCORSimulation {

  val WaitForNextIteration = Environment.waitForNextIteration
  val feeder = csv("TYA_Details.csv").circular

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(Environment.SSCSCORURL)
    .headers(Environment.commonHeader)


  implicit val postHeaders: Map[String, String] = Map(
    "Origin" -> Environment.SSCSCORURL
  )

  def createCORScenario =
    exec(
      feed(feeder),
      SSCSMYA.home,        //  LoginPage.logIn(testUsers.head),
      SSCSMYA.login,
    //  SSCSMYA.enterPostcode,
      SSCSMYA.clickOnEvidenceTab,
      SSCSMYA.clickSubmitEvidenceLink,
      SSCSMYA.selectUploadRadioButton,
      SSCSMYA.uploadDocument_2MB,
    //  SSCSMYA.uploadDocument_3MB,
      SSCSMYA.submitUploadedDocument,
      SSCSMYA.returnToAppealDetails,
      SSCSMYA.Hearing,
      SSCSMYA.logout
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
