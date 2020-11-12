// =========================================================
// Author - Vijay V
// Date - dd/mm/yyyy
// Data dependecy - For this script to run, you need to get a MYA reference. You can get this from SSCS SYA
// =========================================================
package uk.gov.hmcts.reform.sscs.performance.trackyourappeal
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.sscs.performance.simulations.checks.CsrfCheck
import uk.gov.hmcts.reform.sscs.performance.utils._
object SSCSMYA {
  val tyaThinkTime = Environment.tyaThinkTime
  val idamUrl = Environment.IDAMURL
  val env = Environment.env

//=======================================================================================
  // Enter URL for Service
  // Data Dependency - Need MYA reference. This is the Cases id from SYA
  //=======================================================================================

  val home =
  exec(http("SSCSMYA${service}_010_LandingPage")
      .get("/sign-in?tya=${reference}")
      .headers(SSCSMYAHeaders.headers_homepage)
         .check(status.in(200))
          .check(CsrfCheck.save))
   //.pause(tyaThinkTime)


  // =======================================================================================
  // Enter username and password - click Sign In
  // =======================================================================================
  val login =
  exec(http("SSCSMYA${service}_020_005_LoginPage")
       .post(idamUrl + "/login?redirect_uri=https%3A%2F%2Fsscs-cor." + env + ".platform.hmcts.net%2Fsign-in&client_id=sscs&response_type=code&state=${reference}")
    .headers(SSCSMYAHeaders.headers_login)
       .formParam("username", "${email}")
       .formParam("password", "Pass19word")
       .formParam("_csrf", "${csrf}")
       .formParam("save", "Sign in")
       .formParam("selfRegistrationEnabled", "true")
        .check(CsrfCheck.save)
       .check(status.is(200))
     // .check(currentLocation.saveAs("currentPage"))
      .check(regex("Enter the postcode for the appeal").optional.saveAs("postcodecheck"))

  )
    .exec(http("SSCSMYA${service}_020_010_LoginPage_SessionExt")
          .get("/session-extension")
          .headers(SSCSMYAHeaders.headers_20)
          .check(status.in(200,304))
    )

    .doIf("${postcodecheck.exists()}")
    {
      exec(http("SSCSMYA${service}_030_005_EnterPostcode").post("/assign-case").headers(SSCSMYAHeaders.headers_postcode).formParam("_csrf", "${csrf}").formParam("postcode", "${postcode}").formParam("assign-case", "Continue").check(status.is(200))
      )
        .exec(http("SSCSMYA${service}_030_010_EnterPostcode_SessionExt")
          .get("/session-extension")
          .headers(SSCSMYAHeaders.headers_20)
          .check(status.in(200, 304)))
    }

 /* .exec( session => {
         println("postcode check "+session("postcodecheck").as[String])
   // println("page check "+session("currentPage").as[String])
         session
       })*/
   //  .check(CsrfCheck.save)
    //.check(regex("Enter the postcode for the appeal"))


 //.pause(tyaThinkTime)

  // =======================================================================================
  // postcode validation
  // =======================================================================================

  val enterPostcode = exec(http("SSCSMYA${service}_030_005_EnterPostcode")
                           .post("/assign-case")
                            .headers(SSCSMYAHeaders.headers_postcode)
                           .formParam("_csrf", "${csrf}")
                           .formParam("postcode", "${postcode}")
                           .formParam("assign-case", "Continue")
                           .check(status.is(200))
    //  .check(CsrfCheck.save)
  )
.exec(http("SSCSMYA${service}_030_010_EnterPostcode_SessionExt")
      .get("/session-extension")
      .headers(SSCSMYAHeaders.headers_20)
  .check(status.in(200,304)
))
//.pause(tyaThinkTime)

  // =======================================================================================
  // when click on provide evidence tab after login
  // =======================================================================================
val clickOnEvidenceTab=
  exec(http("SSCSMYA${service}_030_005_ClickOnEvidenceTab")
       .get("/task-list")
       .headers(SSCSMYAHeaders.headers_tasklist)
    .check(status.in(200,304))
  )
  .exec(http("SSCSMYA${service}_030_010_ClickOnEvidenceTab_SessionExt")
      .get("/session-extension")
      .headers(SSCSMYAHeaders.headers_20)
    .check(status.in(200,304)))
  //.pause(tyaThinkTime)

// =======================================================================================
// Click on Submit your Evidence here link
// https://sscs-cor.aat.platform.hmcts.net/additional-evidence
// =======================================================================================
val clickSubmitEvidenceLink =
exec(http("SSCSMYA${service}_040_005_ClickOnSubmitEvidence")
   .get("/additional-evidence")
    .headers(SSCSMYAHeaders.headers_tasklist)
   .check(CsrfCheck.save)
   .check(status.is(200)))

 .exec(http("SSCSMYA${service}_040_010_ClickOnSubmitEvidence_SessionExt")
.get("/session-extension")
.headers(SSCSMYAHeaders.headers_20)
   .check(status.in(200,304))
 )
//.pause(tyaThinkTime)


// =======================================================================================
// Click on Upload letters, documents or photos radio button  and Continue
// https://sscs-cor.aat.platform.hmcts.net/additional-evidence/upload
// =======================================================================================

val selectUploadRadioButton =
exec(http("SSCSMYA${service}_050_005_SelectUploadOption")
   .post("/additional-evidence")
       .headers(SSCSMYAHeaders.headers_upload)
   .formParam("additional-evidence-option", "upload")
   .formParam("_csrf", "${csrf}")
   .formParam("continue", "")
  .check(status.is(200))
   .check(css("#additional-evidence-form", "action").saveAs("uploadurl"))
)

   .exec(http("SSCSMYA${service}_050_010_SelectUploadOption_SessionExt")
.get("/session-extension")
.headers(SSCSMYAHeaders.headers_20)
     .check(status.in(200,304))
   )
//.pause(tyaThinkTime)

// =======================================================================================
// Enter free text in describe and then choose a file. Once okay the file, it is uploaded and page is refreshed, below is a request to upload 2MB file
// =======================================================================================
val uploadDoc=
  exec(http("SSCSMYA${service}_0X0_005_UploadDoc2MB")
      .post("${uploadurl}")
      .headers(SSCSMYAHeaders.headers_uploadfile)
    .bodyPart(RawFileBodyPart("additional-evidence-file", "2MB.pdf")
              .fileName("2MB.pdf")
              .transferEncoding("binary")).asMultipartForm
    .check(status.is(200))
    .check(css("#additional-evidence-form", "action").saveAs("uploadurlsubmit2mb"))
  )

    .exec(http("SSCSMYA${service}_060_010_UploadDoc2MB_SessionExt")
          .get("/session-extension")
          .headers(SSCSMYAHeaders.headers_20)
          .check(status.in(200,304))
    )
    .pause(tyaThinkTime)

val upload2MBSubmit=
    exec(http("SSCSMYA${service}_080_005_SubmitEvidence")
          .post("${uploadurlsubmit2mb}")
          .headers(SSCSMYAHeaders.headers_upload2MBcomplete)
          .body(RawFileBody("RecordedSimulationnopostcode_0112_request.txt"))
      .check(status.in(200,304))
    )

      .exec(http("SSCSMYA${service}_080_010_SubmitEvidence_SessionExt")
            .get("/session-extension")
            .headers(SSCSMYAHeaders.headers_20)
            .check(status.in(200,304))
      )
    .pause(tyaThinkTime)


  /*val uploadDocument_2MB=
  exec(http("SSCSMYA${service}_060_005_UploadDoc2MB")
        .post("/additional-evidence/upload")
        .headers(SSCSMYAHeaders.headers_73)
    .bodyPart(RawFileBodyPart("additional-evidence-file", "2MB.pdf")
              .fileName("2MB.pdf")
              .transferEncoding("binary")).asMultipartForm
    .check(status.is(200))
    //.check(css("#additional-evidence-form", "action").saveAs("uploadurlsubmit2mb"))
  )
    .exec(http("SSCSMYA${service}_060_010_UploadDoc2MB_SessionExt")
          .get("/session-extension")
          .headers(SSCSMYAHeaders.headers_20)
      .check(status.in(200,304))
    )*/
   // .pause(tyaThinkTime)

  // =======================================================================================
  // Enter free text in descibe and then choose a file. Once okay the file, it is uploaded and page is refreshed, below is a request to upload 3MB file
  // =======================================================================================

 /* val uploadDocument_3MB=
    exec(http("SSCSMYA${service}_070_005_UploadDoc3MB")
         .post("/additional-evidence/upload")
         .headers(SSCSMYAHeaders.headers_upload3mb)
         .bodyPart(RawFileBodyPart("additional-evidence-file", "3MB.pdf")
                   .fileName("3MB.pdf")
                   .transferEncoding("binary")).asMultipartForm
         .check(status.is(200))
         .check(css("#additional-evidence-form", "action").saveAs("uploadurlsubmitfinal"))
    )
    .exec(http("SSCSMYA${service}_070_010_UploadDoc3MB_SessionExt")
          .get("/session-extension")
          .headers(SSCSMYAHeaders.headers_20)
      .check(status.in(200,304))
    )*/
   // .pause(tyaThinkTime)


// =======================================================================================
// Click - submit envidence to the tribunal.
// Looks like file is uploadedddddddddddddddddjdkdd again?
// Request URL: https://sscs-cor.aat.platform.hmcts.net/additional-evidence/upload?_csrf=VNwfgxc0-kgNrCuGxqeyUtTE2ZCDkNUc__DA
// Request URL: https://sscs-cor.aat.platform.hmcts.net/additional-evidence/confirm
// =======================================================================================

 /* val submitUploadedDocumentFire =
exec(http(" SSCSMYA${service}_080_005_SubmitEvidence")
.post("/additional-evidence/upload")
       .headers(SSCSMYAHeaders.headers_submituploadfire)
    // .headers(SSCSMYAHeaders.headers_83)
      // .body(RawFileBody("RecordedSimulationCOR22062020_0083_request.txt"))
     .body(RawFileBody("RecordedSimulationCORFire_0010_request.txt"))

.check(status.is(200)))

 .exec(http("SSCSMYA${service}_080_010_SubmitEvidence_SessionExt")
.get("/session-extension")
.headers(SSCSMYAHeaders.headers_20)
  .check(status.in(200,304))
 )*/

//.pause(tyaThinkTime)
// =======================================================================================
// clcik return to PIP Appeal after Document is uploaded completely
// =======================================================================================
val returnToAppealDetails =
exec(http("SSCSMYA${service}_090_005_ReturnToAppeal")
.get("/task-list")
.check(status.in(200,304)))

 .exec(http("SSCSMYA${service}_090_010_ReturnToAppeal_SessionExt")
.get("/session-extension")
.headers(SSCSMYAHeaders.headers_20)
   .check(status.in(200,304))
 )

//.pause(tyaThinkTime)
// =======================================================================================
//click on hearing tab
// =======================================================================================
val Hearing =
exec(http("SSCSMYA${service}_100_005_Hearing")
.get("/hearing")
    .headers(SSCSMYAHeaders.headers_hearing)
.check(status.is(200)))
 .exec(http("SSCSMYA${service}_100_010_Hearing_SessionExt")
.get("/session-extension")
.headers(SSCSMYAHeaders.headers_20)
   .check(status.in(200,304))
 )
//.pause(tyaThinkTime)
// =======================================================================================
//Signout
// =======================================================================================
val logout =
exec(http("SSCSMYA${service}_${SignoutNumber}_Signout")
.get("/sign-out")
.check(status.is(200)))

}
