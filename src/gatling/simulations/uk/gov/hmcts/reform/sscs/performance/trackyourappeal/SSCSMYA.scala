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
object SSCSMYA {
  val tyaThinkTime = Environment.tyaThinkTime
  val idamUrl = Environment.IDAMURL

  val headers_fire2MB = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Content-Type" -> "multipart/form-data; boundary=---------------------------198111773715345659193638287702",
    "Origin" -> "https://sscs-cor.perftest.platform.hmcts.net",
    "TE" -> "Trailers",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_fire3MB = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Content-Type" -> "multipart/form-data; boundary=---------------------------1748031016249108562799986968",
    "Origin" -> "https://sscs-cor.perftest.platform.hmcts.net",
    "TE" -> "Trailers",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_submituploadfire = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Content-Type" -> "multipart/form-data; boundary=---------------------------24522327642503733002897372352",
    "Origin" -> "https://sscs-cor.perftest.platform.hmcts.net",
    "TE" -> "Trailers",
    "Upgrade-Insecure-Requests" -> "1")


  // =======================================================================================
  // Enter URL for Service
  // Data Dependency - Need MYA reference. This is the Cases id from SYA
  // =======================================================================================

  val home =
  exec(http("SSCSMYA_010_LandingPage")
      .get("/sign-in?tya=${reference}")
      .headers(SSCSMYAHeaders.headers_homepage)
         .check(status.in(200))
          .check(CsrfCheck.save))
    .pause(tyaThinkTime)


  // =======================================================================================
  // Enter username and password - click Sign In
  // =======================================================================================
  val login =
  exec(http("SSCSMYA_020_005_LoginPage")
       .post(idamUrl + "/login?redirect_uri=https%3A%2F%2Fsscs-cor.perftest.platform.hmcts.net%2Fsign-in&client_id=sscs&response_type=code&state=${reference}")
    .headers(SSCSMYAHeaders.headers_login)
       .formParam("username", "${email}")
       .formParam("password", "Pass19word")
       .formParam("_csrf", "${csrf}")
       .formParam("save", "Sign in")
       .formParam("selfRegistrationEnabled", "true")
       .check(status.is(200))
   //  .check(CsrfCheck.save)
    //.check(regex("Enter the postcode for the appeal"))
  )
   .exec(http("SSCSMYA_020_010_LoginPage_SessionExt")
          .get("/session-extension")
          .headers(SSCSMYAHeaders.headers_20)
     .check(status.in(200,304))
   )
  .pause(tyaThinkTime)

  // =======================================================================================
  // postcode validation
  // =======================================================================================

  val enterPostcode = exec(http("SSCSMYA_030_005_EnterPostcode")
                           .post("/assign-case")
                            .headers(SSCSMYAHeaders.headers_postcode)
                           .formParam("_csrf", "${csrf}")
                           .formParam("postcode", "${postcode}")
                           .formParam("assign-case", "Continue")
                           .check(status.is(200))
    //  .check(CsrfCheck.save)
  )
.exec(http("SSCSMYA_030_010_EnterPostcode_SessionExt")
      .get("/session-extension")
      .headers(SSCSMYAHeaders.headers_20)
  .check(status.in(200,304)
))
                  .pause(tyaThinkTime)

  // =======================================================================================
  // when click on provide evidence tab after login
  // =======================================================================================
val clickOnEvidenceTab=
  exec(http("SSCSMYA_040_005_ClickOnEvidenceTab")
       .get("/task-list")
       .headers(SSCSMYAHeaders.headers_tasklist)
    .check(status.in(200,304))
  )
  .exec(http("SSCSMYA_040_010_ClickOnEvidenceTab_SessionExt")
      .get("/session-extension")
      .headers(SSCSMYAHeaders.headers_20)
    .check(status.is(200)))
    .pause(tyaThinkTime)



// =======================================================================================
// Click on Submit your Evidence here link
// https://sscs-cor.aat.platform.hmcts.net/additional-evidence
// =======================================================================================
val clickSubmitEvidenceLink =
exec(http("SSCSMYA_050_005_ClickOnSubmitEvidence")
   .get("/additional-evidence")
    .headers(SSCSMYAHeaders.headers_tasklist)
   .check(CsrfCheck.save)
   .check(status.is(200)))

 .exec(http("SSCSMYA_050_010_ClickOnSubmitEvidence_SessionExt")
.get("/session-extension")
.headers(SSCSMYAHeaders.headers_20)
   .check(status.in(200,304))
 )
.pause(tyaThinkTime)


// =======================================================================================
// Click on Upload letters, documents or photos radio button  and Continue
// https://sscs-cor.aat.platform.hmcts.net/additional-evidence/upload
// =======================================================================================

val selectUploadRadioButton =
exec(http("SSCSMYA_060_005_SelectUploadOption")
   .post("/additional-evidence")
       .headers(SSCSMYAHeaders.headers_upload)
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

   .exec(http("SSCSMYA_060_010_SelectUploadOption_SessionExt")
.get("/session-extension")
.headers(SSCSMYAHeaders.headers_20)
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

  val uploadDocument2MB=
  exec(http("SSCSMYA_070_005_UploadDoc2MB")
        .post("${uploadurl}")
        .headers(headers_fire2MB)
        .body(RawFileBody("RecordedSimulationCORFire_0000_request.txt")).asMultipartForm
    .check(status.is(200))
    .check(css("#additional-evidence-form", "action").saveAs("uploadurlsubmit2mb"))
  )
    .exec(http("SSCSMYA_070_010_UploadDoc2MB_SessionExt")
          .get("/session-extension")
          .headers(SSCSMYAHeaders.headers_20)
          .check(status.in(200,304)))
    .pause(15)


  val uploadDocument3MB=
    exec(http("SSCSMYA_070_005_UploadDoc3MB")
         .post("${uploadurlsubmit2mb}")
         .headers(headers_fire3MB)
      .body(RawFileBody("RecordedSimulationCORFire_0005_request.txt")).asMultipartForm
         .check(status.is(200))
         .check(css("#additional-evidence-form", "action").saveAs("uploadurlsubmit3mb"))
    )
      .exec(http("SSCSMYA_070_010_UploadDoc3MB_SessionExt")
            .get("/session-extension")
            .headers(SSCSMYAHeaders.headers_20)
            .check(status.in(200,304))
      )
      .pause(15)




  val uploadDocument_2MB=
  exec(http("SSCSMYA_070_005_UploadDoc2MB")
        .post("${uploadurl}")
        .headers(SSCSMYAHeaders.headers_73)
    .bodyPart(RawFileBodyPart("additional-evidence-file", "2MB.pdf")
              .fileName("2MB.pdf")
              .transferEncoding("binary")).asMultipartForm
    .check(status.is(200))
    .check(css("#additional-evidence-form", "action").saveAs("uploadurlsubmit2mb"))
  )
    .exec(http("SSCSMYA_070_010_UploadDoc2MB_SessionExt")
          .get("/session-extension")
          .headers(SSCSMYAHeaders.headers_20)
      .check(status.in(200,304))
    )
    .pause(30)

  val uploadDocument_3MB=
    exec(http("SSCSMYA_070_005_UploadDoc3MB")
         .post("${uploadurlsubmit2mb}")
         .headers(SSCSMYAHeaders.headers_upload3mb)
         .bodyPart(RawFileBodyPart("additional-evidence-file", "3MB.pdf")
                   .fileName("3MB.pdf")
                   .transferEncoding("binary")).asMultipartForm
         .check(status.is(200))
         .check(css("#additional-evidence-form", "action").saveAs("uploadurlsubmitfinal"))
    )
    .exec(http("SSCSMYA_070_010_UploadDoc3MB_SessionExt")
          .get("/session-extension")
          .headers(SSCSMYAHeaders.headers_20)
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
exec(http("SSCSMYA_080_005_SubmitEvidence")
     .post("${uploadurlsubmit3mb}")
     .headers(SSCSMYAHeaders.headers_83)
     .header("Content-Type", "application/octet-stream")
     /*.formParam("additional-evidence-description", "asasasasasasasasasasasasas")
     .formParam("additional-evidence-file", "2MB.pdf")
    // .formParam("additional-evidence-file", "3MB.pdf")
     .formParam("buttonSubmit", "Submit evidence to the tribunal")*/
     /* .formParam("form_data","{\"additional-evidence-description\":\"adding text in a box and choosing a file\"}")
        .formParam("form_data","{\"additional-evidence-file\":\"(binary)\"}")
        .formParam("form_data","{\"buttonSubmit\":\"Submit evidence to the tribunal\"}")
 */

     .body(RawFileBody("RecordedSimulationlatestupdate_0082_request.txt"))

     //.bodyPart(ElFileBodyPart("RecordedSimulationlatestupdate_0082_request.txt"))
     //.check(CsrfCheck.save)
     .check(status.is(200)))

  .exec(http("SSCSMYA_080_010_SubmitEvidence_SessionExt")
        .get("/session-extension")
        .headers(SSCSMYAHeaders.headers_20)
        .check(status.in(200,304))
  )


  val submitUploadedDocumentFire =
exec(http("SSCSMYA_080_005_SubmitEvidence")
.post("${uploadurlsubmitfinal}")
       .headers(headers_submituploadfire)
       .body(RawFileBody("RecordedSimulationCORFire_0010_request.txt"))

     //.bodyPart(ElFileBodyPart("RecordedSimulationlatestupdate_0082_request.txt"))
//.check(CsrfCheck.save)
.check(status.is(200)))

 .exec(http("SSCSMYA_080_010_SubmitEvidence_SessionExt")
.get("/session-extension")
.headers(SSCSMYAHeaders.headers_20)
  .check(status.in(200,304))
 )

.pause(tyaThinkTime)
// =======================================================================================
// clcik return to PIP Appeal after Document is uploaded completely
// =======================================================================================
val returnToAppealDetails =
exec(http("SSCSMYA_090_005_ReturnToAppeal")
.get("/task-list")
.check(status.is(200)))

 .exec(http("SSCSMYA_090_010_ReturnToAppeal_SessionExt")
.get("/session-extension")
.headers(SSCSMYAHeaders.headers_20)
   .check(status.in(200,304))
 )

.pause(tyaThinkTime)
// =======================================================================================
//click on hearing tab
// =======================================================================================
val Hearing =
exec(http("SSCSMYA_100_005_Hearing")
.get("/hearing")
    .headers(SSCSMYAHeaders.headers_hearing)
.check(status.is(200)))
 .exec(http("SSCSMYA_100_010_Hearing_SessionExt")
.get("/session-extension")
.headers(SSCSMYAHeaders.headers_20)
   .check(status.in(200,304))
 )
.pause(tyaThinkTime)
// =======================================================================================
//Signout
// =======================================================================================
val logout =
exec(http("SSCSMYA_110_Signout")
.get("/sign-out")
.check(status.is(200)))
.pause(tyaThinkTime)
}