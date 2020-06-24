package uk.gov.hmcts.reform.sscs.performance.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
//import uk.gov.hmcts.reform.sscs.performance.processes._
import uk.gov.hmcts.reform.sscs.performance.trackyourappeal.SSCSCOR
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
      SSCSCOR.home,        //  LoginPage.logIn(testUsers.head),
      SSCSCOR.login,
      SSCSCOR.enterPostcode,
      SSCSCOR.clickOnEvidenceTab,
      SSCSCOR.clickSubmitEvidenceLink,
      SSCSCOR.selectUploadRadioButton,
      SSCSCOR.uploadDocument_2MB,
     // SSCSCOR.uploadDocument_3MB,
      SSCSCOR.submitUploadedDocument,
      SSCSCOR.returnToAppealDetails,
      SSCSCOR.Hearing,
      SSCSCOR.logout
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
