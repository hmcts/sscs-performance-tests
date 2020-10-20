package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.sscs.performance.simulations.checks.CsrfCheck
import uk.gov.hmcts.reform.sscs.performance.simulations.checks.CsrfCheck.{csrfParameter, csrfTemplate}
//import uk.gov.hmcts.reform.sscs.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object UploadEvidence{

  val thinktime = Environment.thinkTime

  val headers_19 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Cache-Control" -> "max-age=0",
    "Connection" -> "keep-alive",
    "Origin" -> "https://sscs-tribunals-frontend-sprod.service.core-compute-sprod.internal",
    "Upgrade-Insecure-Requests" -> "1",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")


  val headers_1MB = Map(
    "Accept" -> "*/*",
    "Accept-Language" -> "en-GB",
    "Connection" -> "Keep-Alive",
    "Content-Type" -> "multipart/form-data; boundary=---------------------------7e22933110236",
    "Origin" -> "https://sscs-tribunals-frontend-sprod.service.core-compute-sprod.internal",
    "X-Requested-With" -> "XMLHttpRequest")

  val headers_2MB = Map(
    "Accept" -> "*/*",
    "Accept-Language" -> "en-GB",
    "Connection" -> "Keep-Alive",
    "Content-Type" -> "multipart/form-data; boundary=---------------------------7e23513a10236",
    "Origin" -> "https://sscs-tribunals-frontend-sprod.service.core-compute-sprod.internal",
    "X-Requested-With" -> "XMLHttpRequest")

  val headers_3MB = Map(
    "Accept" -> "*/*",
    "Accept-Language" -> "en-GB",
    "Connection" -> "Keep-Alive",
    "Content-Type" -> "multipart/form-data; boundary=---------------------------7e22f4810236",
    "Origin" -> "https://sscs-tribunals-frontend-sprod.service.core-compute-sprod.internal",
    "X-Requested-With" -> "XMLHttpRequest")

  val headers_2MB_1 = Map(
    "Accept" -> "*/*",
    "Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryp95uCCxYktALnk3E",
    "Origin" -> "https://sscs-tribunals-frontend-sprod.service.core-compute-sprod.internal",
    "X-Requested-With" -> "XMLHttpRequest")

  val headers_2MB_2 = Map(
    "Accept" -> "*/*",
    "Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundarys6xSGb1uBlb76oH2",
    "Origin" -> "https://sscs-tribunals-frontend-sprod.service.core-compute-sprod.internal",
    "X-Requested-With" -> "XMLHttpRequest")


//following request has to be changed to yes when using upload files

  // =======================================================================================
  // Indicate whether you wish to provide evidence
  // =======================================================================================

  val evidenceProvide=
    exec(http("TX37_SSCS_EvidenceProvide")
      .post("/evidence-provide")
      .formParam("evidenceProvide", "no")
     // .formParam(csrfParameter, csrfTemplate)
      .check(status.in(200))
      // .check(CsrfCheck.save)
    )
      .pause(thinktime)

  // =======================================================================================
  // Upload evidence (1)
  // =======================================================================================

  val evidenceUpload_2MB_1=
    exec(http("TX38_SSCS_EvidenceUpload_2MB_1")

      .post("/evidence-upload/item-0")
      .headers(headers_2MB_1)
      .header("CSRF-Token", csrfTemplate)
      .body(RawFileBody("RecordedSimulationtoday1112_0116_request.txt")))
      //.pause(10)
      .exec(http("TX25_SSCS_EvidenceUpload_2MB_1_GET")
        .get("/evidence-upload")
        .check(status.is(200))
      .check(CsrfCheck.save)
    )
      .pause(thinktime)

  // =======================================================================================
  // Upload evidence (2)
  // =======================================================================================

  val evidenceUpload_2MB_2=
    exec(http("TX39_SSCS_EvidenceUpload_2MB_2")
      .post("/evidence-upload/item-1")
      .headers(headers_2MB_2)
      .header("CSRF-Token", csrfTemplate)
      .body(RawFileBody("RecordedSimulationtoday1112_0128_request.txt")))

     // .pause(10)
      .exec(http("TX27_SSCS_EvidenceUpload_2MB_2_GET")
        .get("/evidence-upload")
        .check(status.is(200))
      .check(CsrfCheck.save)
      )
    .pause(thinktime)

  // =======================================================================================
  // Upload evidence (3)
  // =======================================================================================

  val evidenceUpload_3MB=
    exec(http("TX40_SSCS_EvidenceUpload_3MB")
      .post("/evidence-upload/item-2")
      .headers(headers_3MB)
      .header("Content-Type", "application/json")
      .body(RawFileBody("RecordedSimulationlatest_0043_request.txt")))
    //  .pause(10)
      .exec(http("TX29_SSCS_EvidenceUpload_3MB_GET")
        .get("/evidence-upload")
        .check(status.is(200))
      .check(CsrfCheck.save)
    )
    .pause(thinktime)

  // =======================================================================================
  // Complete uploading your evidence
  // =======================================================================================

  val evidenceUploadComplete=

    exec(http("TX41_SSCS_EvidenceUpload_Complete")
      .post("/evidence-upload")
      .formParam(csrfParameter, csrfTemplate)
      .headers(headers_19)
      .header("Content-Length", "0")
      .check(status.in(200))
      .check(regex("Describe the evidence")))
      .pause(thinktime)


  // =======================================================================================
  // Describe your evidence
  // =======================================================================================

  val evidenceDescription=
    exec(http("TX42_SSCS_Evidence_Description")
      .post("/evidence-description")
      .formParam("describeTheEvidence", "Evidence - I am Performance Testing")
      .formParam(csrfParameter, csrfTemplate)
      .check(status.in(200))
      .check(regex("The appeal hearing")))
      .pause(5)
      .pause(thinktime)

}
