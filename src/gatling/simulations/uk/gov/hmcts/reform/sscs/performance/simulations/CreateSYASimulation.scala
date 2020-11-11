package uk.gov.hmcts.reform.sscs.performance.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmcts.reform.sscs.performance.submityourappeal.{UploadEvidence, _}
//import uk.gov.hmcts.reform.sscs.performance.processes._
import uk.gov.hmcts.reform.sscs.performance.utils.Environment
//import uk.gov.hmcts.reform.sscs.performance.simulations.lifecycle.SimulationHooks
//import uk.gov.hmcts.reform.idam.{User,LoginPage}

object  CreateSYASimulation {

  val WaitForNextIteration = Environment.waitForNextIteration


  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(Environment.sscsSYAURL)
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
      StartAppealPage.environment,
      StartAppealPage.entry,
      StartAppealPage.benefitType,
      StartAppealPage.languagePreference,
      StartAppealPage.postCodeCheck,
      StartAppealPage.independenceBeforeLogin,
      StartAppealPage.saveLater,
      StartAppealPage.login1,
      StartAppealPage.login2,
      //StartAppealPage.checkYourAppeal1,
      StartAppealPage.independenceAfterLogin,
      Appellant.haveMRN,
      Appellant.MRNDate,
      Appellant.DWPIssuingOffice,
      StartAppealPage.areYouAnAppointee,
      Appellant.name,
      Appellant.DOB,
      Appellant.niNumber,
      Appellant.contactDetails1,
      Appellant.contactDetails2,
      Appellant.contactDetails3,
      Appellant.textReminders,
      Representative.representative,
      //Representative.repDetails,
      AppealingReason.reasonForAppealing,
      AppealingReason.finalReasonForAppealing,
      UploadEvidence.evidenceProvide,
     // UploadEvidence.evidenceUpload_GET,
    //UploadEvidence.evidenceUpload_1MB,
    //UploadEvidence.evidenceUpload_2MB,
    //UploadEvidence.evidenceUpload_3MB,
     /* UploadEvidence.evidenceUpload_2MB_1,
      UploadEvidence.evidenceUpload_2MB_2,
     UploadEvidence.evidenceUploadComplete,
     UploadEvidence.evidenceDescription,*/
      AppealingReason.attendHearing,
      //AppealingReason.hearingOptions,
      AppealingReason.supportHearing,
      AppealingReason.hearingAvailability,
      AppealingReason.checkYourAppeal
      AppealingReason.signOut

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
