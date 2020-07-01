package uk.gov.hmcts.reform.sscs.performance.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmcts.reform.sscs.performance.utils.Feeders
//import uk.gov.hmcts.reform.sscs.performance.processes._
import uk.gov.hmcts.reform.sscs.performance.trackyourappeal.SSCSMYA
import uk.gov.hmcts.reform.sscs.performance.utils.Environment
//import uk.gov.hmcts.reform.sscs.performance.simulations.lifecycle.SimulationHooks
//import uk.gov.hmcts.reform.idam.{User,LoginPage}



object  CreateCORSimulation {

  val WaitForNextIteration = Environment.waitForNextIteration
  val thinktime = Environment.thinkTime
  val thinktimenoupload = Environment.thinkTimeNoUpload
  val feeder = csv("TYA_Details.csv").circular
  val feederForUpload = csv("TYA_Details_Upload.csv").circular

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(Environment.SSCSCORURL)
    .headers(Environment.commonHeader)


  implicit val postHeaders: Map[String, String] = Map(
    "Origin" -> Environment.SSCSCORURL
  )

  //=======================================================================================
  // Following scenario is for MYA including upload file
  //=======================================================================================


  def createCORScenarioWithUpload =
    exec(
      feed(feederForUpload),
      feed(Feeders.DataFeederForUpload),
      SSCSMYA.home,
      pause(thinktime),//  LoginPage.logIn(testUsers.head),
      SSCSMYA.login,
      pause(thinktime),
    //  SSCSMYA.enterPostcode,
      SSCSMYA.clickOnEvidenceTab,
      pause(thinktime),
      SSCSMYA.clickSubmitEvidenceLink,
      pause(thinktime),
      SSCSMYA.selectUploadRadioButton,
      pause(thinktime),
      SSCSMYA.uploadDocument_2MB,
      pause(thinktime),
     SSCSMYA.uploadDocument_3MB,
      pause(thinktime),
      SSCSMYA.submitUploadedDocumentFire,
      pause(thinktime),
      SSCSMYA.returnToAppealDetails,
      pause(thinktime),
      SSCSMYA.Hearing,
      pause(thinktime),
      SSCSMYA.logout
    )

  pace(WaitForNextIteration)

  //=======================================================================================
  // Following scenario is for MYA with no upload file
  //=======================================================================================


  def createCORScenarioNoUpload =
    exec(
      feed(feeder),
      feed(Feeders.DataFeederForNoUpload),
      SSCSMYA.home,
      pause(thinktimenoupload),//  LoginPage.logIn(testUsers.head),
      SSCSMYA.login,
      pause(thinktimenoupload),
      SSCSMYA.clickOnEvidenceTab,
      pause(thinktimenoupload),
      SSCSMYA.Hearing,
      pause(thinktimenoupload),
      SSCSMYA.logout
    )




}
