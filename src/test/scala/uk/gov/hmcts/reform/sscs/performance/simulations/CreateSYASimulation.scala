package uk.gov.hmcts.reform.sscs.performance.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmcts.reform.sscs.performance.submityourappeal
import uk.gov.hmcts.reform.sscs.performance.submityourappeal.{UploadEvidence, _}
//import uk.gov.hmcts.reform.sscs.performance.processes._
import uk.gov.hmcts.reform.sscs.performance.trackyourappeal.TrackYourAppeal
import uk.gov.hmcts.reform.sscs.performance.utils.Environment
//import uk.gov.hmcts.reform.cmc.performance.simulations.lifecycle.SimulationHooks
//import uk.gov.hmcts.reform.idam.{User,LoginPage}

object  CreateSYASimulation {

  val WaitForNextIteration = Environment.waitForNextIteration


  val httpProtocol: HttpProtocolBuilder = http
    .baseURL(Environment.sscsSYAURL)
    .headers(Environment.commonHeader)

  implicit val postHeaders: Map[String, String] = Map(
    "Origin" -> Environment.sscsSYAURL
  )

  def createSYAScenario =
    exec(
    // LandingPage.landingPage,
     // LandingPage.beforeYouAppeal,
     // LandingPage.helpWithAppeal,
     // LandingPage.startAnAppeal,
      StartAppealPage.entry,
      StartAppealPage.benifitType,
      StartAppealPage.postCodeCheck,
      StartAppealPage.areYouAnAppointee,
    //  StartAppealPage.independance,
      Appealant.haveMRN,
      Appealant.DWPIssuingOffice,
      Appealant.MRNDate,
      Appealant.name,
      Appealant.DOB,
      Appealant.niNumber,
      Appealant.contactDetails,
      Appealant.textRemainders,
      Representative.representative,
      Representative.repDetails,
      AppealingReason.reasonForAppealing,
      UploadEvidence.evidenceProvide,
    //  UploadEvidence.evidenceUpload_GET,
   // UploadEvidence.evidenceUpload_1MB,
   //  UploadEvidence.evidenceUpload_2MB,
   // UploadEvidence.evidenceUpload_3MB,
   //  UploadEvidence.evidenceUploadComplete,
    // UploadEvidence.evidenceDescription,
      AppealingReason.attendHearing,
      AppealingReason.supportHearing,
      AppealingReason.checkYourAppeal

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
