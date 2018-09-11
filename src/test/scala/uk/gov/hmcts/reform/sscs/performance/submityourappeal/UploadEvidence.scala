package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import uk.gov.hmcts.reform.cmc.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
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





  val evidenceProvide=
    exec(http("TX22_SSCS_EvidenceProvide")
      .post("/evidence-provide")
      .formParam("evidenceProvide", "yes"))
      .pause(thinktime)


  val evidenceUpload_1MB=
    exec(http("TX24_SSCS_EvidenceUpload_1MB")

      .post("/evidence-upload/item-0")
      .headers(headers_1MB)
      .body(RawFileBody("RecordedSimulationlatest_0015_request.txt")))
      .pause(10)
      .exec(http("TX25_SSCS_EvidenceUpload_1MB_GET")
        .get("/evidence-upload")
        .check(status.is(200))
      )

  val evidenceUpload_2MB=
    exec(http("TX26_SSCS_EvidenceUpload_2MB")
      .post("/evidence-upload/item-1")
      .headers(headers_2MB)
      .body(RawFileBody("RecordedSimulationlatest_0030_request.txt")))


      .pause(10)
      .exec(http("TX27_SSCS_EvidenceUpload_2MB_GET")
        .get("/evidence-upload")
        .check(status.is(200))
      )

  val evidenceUpload_3MB=
    exec(http("TX28_SSCS_EvidenceUpload_3MB")
      .post("/evidence-upload/item-2")
      .headers(headers_3MB)
      .body(RawFileBody("RecordedSimulationlatest_0043_request.txt")))


      .pause(10)
      .exec(http("TX29_SSCS_EvidenceUpload_3MB_GET")
        .get("/evidence-upload")
        .check(status.is(200)))

  val evidenceUploadComplete=

    exec(http("TX30_SSCS_EvidenceUpload_Complete")
      .post("/evidence-upload")
      .headers(headers_19)
      .header("Content-Length", "0")
      .check(regex("Describe the evidence")))
      .pause(5)



  val evidenceDescription=
    exec(http("TX31_SSCS_Evidence_Description")
      .post("/evidence-description")
      .formParam("describeTheEvidence", "Evidence - I am Performance Testing")
      .check(regex("The appeal hearing")))
      .pause(5)

}
