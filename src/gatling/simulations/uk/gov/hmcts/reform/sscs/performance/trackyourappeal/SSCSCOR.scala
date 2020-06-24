// =========================================================
// Author - Vijay V
// Date - dd/mm/yyyy
// Data dependecy - For this script to run, you need to get a MYA reference. You can get this from SSCS SYA
// =========================================================
package uk.gov.hmcts.reform.sscs.performance.trackyourappeal
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.sscs.performance.simulations.checks.CsrfCheck
//import uk.gov.hmcts.reform.sscs.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._
object SSCSCOR {
  val tyaThinkTime = Environment.tyaThinkTime
  val idamUrl = Environment.IDAMURL

  //below are the headers
  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Accept-Encoding" -> "gzip, deflate",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Upgrade-Insecure-Requests" -> "1",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

  val headers_login = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-US,en;q=0.9",
    "cache-control" -> "max-age=0",
    "origin" -> "https://idam-web-public.perftest.platform.hmcts.net",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1",
    "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

  val headers_9 = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "cache-control" -> "max-age=0",
    "content-type" -> "multipart/form-data; boundary=----WebKitFormBoundaryuOBA1cG2BApZkrL6",
    "origin" -> "https://sscs-cor.perftest.platform.hmcts.net",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1")

  val headers_20 = Map(
    "accept" -> "application/json, text/plain, */*",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-US,en;q=0.9",
    "sec-fetch-dest" -> "empty",
    "sec-fetch-mode" -> "cors",
    "sec-fetch-site" -> "same-origin",
    "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

  val headers_postcode = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-US,en;q=0.9",
    "cache-control" -> "max-age=0",
    "origin" -> "https://sscs-cor.perftest.platform.hmcts.net",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1",
    "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

  val headers_tasklist = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-US,en;q=0.9",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1",
    "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

  val headers_hearing = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-US,en;q=0.9",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1",
    "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")


  val headers_upload = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-US,en;q=0.9",
    "cache-control" -> "max-age=0",
    "origin" -> "https://sscs-cor.perftest.platform.hmcts.net",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1",
    "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

  val headers_73 = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-US,en;q=0.9",
    "cache-control" -> "max-age=0",
    "content-type" -> "multipart/form-data; boundary=----WebKitFormBoundaryq7pXscVFWTLISBg4",
    "origin" -> "https://sscs-cor.perftest.platform.hmcts.net",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1",
    "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")


  val headers_upload3mb = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-US,en;q=0.9",
    "cache-control" -> "max-age=0",
    "content-type" -> "multipart/form-data; boundary=----WebKitFormBoundary1zIKvDeQ1iOj4qPy",
    "origin" -> "https://sscs-cor.perftest.platform.hmcts.net",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1",
    "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

  val headers_83 = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-US,en;q=0.9",
    "cache-control" -> "max-age=0",
    "content-type" -> "multipart/form-data; boundary=----WebKitFormBoundaryjacOdkygBa1u93KL",
    "origin" -> "https://sscs-cor.perftest.platform.hmcts.net",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1",
    "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")
  // =======================================================================================
  // Enter URL for Service
  // Data Dependency - Need MYA reference. This is the Cases id from SYA
  // =======================================================================================

  val home =
  exec(http("SSCSCOR_010_LandingPage")
      .get("/sign-in?tya=${reference}")
         .check(status.in(200))
          .check(CsrfCheck.save))
    .pause(tyaThinkTime)


  // =======================================================================================
  // Enter username and password - click Sign In
  // =======================================================================================
  val login =
  exec(http("SSCSCOR_020_005_LoginPage")
       .post(idamUrl + "/login?redirect_uri=https%3A%2F%2Fsscs-cor.perftest.platform.hmcts.net%2Fsign-in&client_id=sscs&response_type=code&state=${reference}")
    .headers(headers_login)
       .formParam("username", "${email}")
       .formParam("password", "Pass19word")
       .formParam("_csrf", "${csrf}")
       .formParam("save", "Sign in")
       .formParam("selfRegistrationEnabled", "true")
       .check(status.is(200))
     .check(CsrfCheck.save)
    //.check(regex("Enter the postcode for the appeal"))
  )
   .exec(http("SSCSCOR_020_010_LoginPage_SessionExt")
          .get("/session-extension")
          .headers(headers_20)
     .check(status.in(200,304))
   )
  .pause(tyaThinkTime)

  // =======================================================================================
  // postcode validation
  // =======================================================================================

  val enterPostcode = exec(http("SSCSCOR_030_005_EnterPostcode")
                           .post("/assign-case")
                            .headers(headers_postcode)
                           .formParam("_csrf", "${csrf}")
                           .formParam("postcode", "${postcode}")
                           .formParam("assign-case", "Continue")
                           .check(status.is(200))
    //  .check(CsrfCheck.save)
  )
.exec(http("SSCSCOR_030_010_EnterPostcode_SessionExt")
      .get("/session-extension")
      .headers(headers_20)
  .check(status.in(200,304)
))
                  .pause(tyaThinkTime)

  // =======================================================================================
  // when click on provide evidence tab after login
  // =======================================================================================
val clickOnEvidenceTab=
  exec(http("SSCSCOR_040_005_ClickOnEvidenceTab")
       .get("/task-list")
       .headers(headers_tasklist)
    .check(status.in(200,304))
  )
  .exec(http("SSCSCOR_040_010_ClickOnEvidenceTab_SessionExt")
      .get("/session-extension")
      .headers(headers_20)
    .check(status.is(200)))
    .pause(tyaThinkTime)



// =======================================================================================
// Click on Submit your Evidence here link
// https://sscs-cor.aat.platform.hmcts.net/additional-evidence
// =======================================================================================
val clickSubmitEvidenceLink =
exec(http("SSCSCOR_050_005_ClickOnSubmitEvidence")
   .get("/additional-evidence")
    .headers(headers_tasklist)
   .check(CsrfCheck.save)
   .check(status.is(200)))

 .exec(http("SSCSCOR_050_010_ClickOnSubmitEvidence_SessionExt")
.get("/session-extension")
.headers(headers_20)
   .check(status.in(200,304))
 )
.pause(tyaThinkTime)


// =======================================================================================
// Click on Upload letters, documents or photos radio button  and Continue
// https://sscs-cor.aat.platform.hmcts.net/additional-evidence/upload
// =======================================================================================

val selectUploadRadioButton =
exec(http("SSCSCOR_060_005_SelectUploadOption")
   .post("/additional-evidence")
       .headers(headers_upload)
   .formParam("additional-evidence-option", "upload")
   .formParam("_csrf", "${csrf}")
   .formParam("continue", "")
  .check(status.is(200))
   // .check(CsrfCheck.save)
   //.check(currentLocation.saveAs("uploadlocation"))
   //.check(regex("additional-evidence-form", "(?<=code=)(.*)(?=&scope)").saveAs("authcode"))
   // .check( regex( "(?action=)(.*)(?" method=)" )).saveAs("uploadcsrf") )
   //  .check(css("input[name='additional-evidence-form']", "action").saveAs("uploadcsrf"))
   // .check( regex( "(action=\")(.*)(\" method=\"POST\"" ).saveAs("uploadurl"))
   // .check( regex( "(?=action=\")(.*)(?=\" method=)").saveAs("uploadurl"))
   .check(css("#additional-evidence-form", "action").saveAs("uploadurl"))
//  .check( regex( "(action="/.*"")(\" method=)").saveAs("uploadurl"))
//      //.check(bodyString("<form class="--padding-top-m" id="additional-evidence-form" name="additional-evidence-form" action="/.*" ").saveAs("uploadurl"))
)

   .exec(http("SSCSCOR_060_010_SelectUploadOption_SessionExt")
.get("/session-extension")
.headers(headers_20)
     .check(status.in(200,304))
   )
.pause(tyaThinkTime)




// =======================================================================================
// Enter free text in descibe and then choose a file. Once okay the file, it is uploaded and page is refreshed
// =======================================================================================
// Request URL: https://sscs-cor.aat.platform.hmcts.net/additional-evidence/upload?_csrf=V6IcxtPQ-U0C-zL2ajMEnRM7_zny-wESVjuc
/*val AddnlEviUpload_Post =
exec(http("TX04_SSCS_TYA_Evidence_Upload1")
   // .post("/additional-evidence/upload?_csrf=${csrf}")
   .post("${uploadurl}")
   .headers(headers_9)
   // .check(CsrfCheck.save)
   .check(status.is(200))
  .check(css("#additional-evidence-form", "action").saveAs("uploadurlsubmit"))

)
    .exec(http("TX04_SSCS_TYA_Evidence_Upload1_SessionExt")
  .get("/session-extension")
  .headers(headers_20))
.pause(tyaThinkTime)*/

val uploadDocument_2MB=
  exec(http("SSCSCOR_070_005_UploadDoc2MB")
        .post("${uploadurl}")
        .headers(headers_73)
    .bodyPart(RawFileBodyPart("file", "2MB.pdf")
              .fileName("2MB.pdf")
              .transferEncoding("binary")).asMultipartForm
    .check(status.is(200))
    .check(css("#additional-evidence-form", "action").saveAs("uploadurlsubmit2mb"))
  )
    .exec(http("SSCSCOR_070_010_UploadDoc2MB_SessionExt")
          .get("/session-extension")
          .headers(headers_20)
      .check(status.in(200,304))
    )
    .pause(tyaThinkTime)

  val uploadDocument_3MB=
    exec(http("SSCSCOR_070_005_UploadDoc3MB")
         .post("${uploadurlsubmit2mb}")
         .headers(headers_upload3mb)
         .bodyPart(RawFileBodyPart("file", "3MB.pdf")
                   .fileName("3MB.pdf")
                   .transferEncoding("binary")).asMultipartForm
         .check(status.is(200))
         .check(css("#additional-evidence-form", "action").saveAs("uploadurlsubmitfinal"))
    )
    .exec(http("SSCSCOR_070_010_UploadDoc3MB_SessionExt")
          .get("/session-extension")
          .headers(headers_20)
      .check(status.in(200,304))
    )
    .pause(tyaThinkTime)

  // Missing this call - https://sscs-cor.aat.platform.hmcts.net/additional-evidence/upload
// =======================================================================================
// Click - submit envidence to the tribunal.
// Looks like file is uploadedddddddddddddddddjdkdd again?
// Request URL: https://sscs-cor.aat.platform.hmcts.net/additional-evidence/upload?_csrf=VNwfgxc0-kgNrCuGxqeyUtTE2ZCDkNUc__DA
// Request URL: https://sscs-cor.aat.platform.hmcts.net/additional-evidence/confirm
// =======================================================================================
val submitUploadedDocument =
exec(http("SSCSCOR_080_005_SubmitEvidence")
.post("${uploadurlsubmit2mb}")
       .headers(headers_83)
.body(RawFileBody("RecordedSimulationlatestupdate_0082_request.txt"))
//.check(CsrfCheck.save)
.check(status.is(200)))

 .exec(http("SSCSCOR_080_010_SubmitEvidence_SessionExt")
.get("/session-extension")
.headers(headers_20)
  .check(status.in(200,304))
 )

.pause(tyaThinkTime)
// =======================================================================================
// clcik return to PIP Appeal after Document is uploaded completely
// =======================================================================================
val returnToAppealDetails =
exec(http("SSCSCOR_090_005_ReturnToAppeal")
.get("/task-list")
.check(status.is(200)))

 .exec(http("SSCSCOR_090_010_ReturnToAppeal_SessionExt")
.get("/session-extension")
.headers(headers_20)
   .check(status.in(200,304))
 )

.pause(tyaThinkTime)
// =======================================================================================
//click on hearing tab
// =======================================================================================
val Hearing =
exec(http("SSCSCOR_100_005_Hearing")
.get("/hearing")
    .headers(headers_hearing)
.check(status.is(200)))
 .exec(http("SSCSCOR_100_010_Hearing_SessionExt")
.get("/session-extension")
.headers(headers_20)
   .check(status.in(200,304))
 )
.pause(tyaThinkTime)
// =======================================================================================
//Signout
// =======================================================================================
val logout =
exec(http("SSCSCOR_110_Signout")
.get("/sign-out")
.check(status.is(200)))
.pause(tyaThinkTime)
}