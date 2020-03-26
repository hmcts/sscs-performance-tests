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

  val feeder = csv("TYA_Details.csv").circular

  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {
  val home = feed(feeder).exec(http("TX01_SSCS_COR_LandingPage")
    .get("/sign-in?tya=${reference}")
    .check(status.is(200))
    // .check(css("input[name='_csrf']", "value").saveAs("csrfToken")))
    .check(CsrfCheck.save))
    .pause(tyaThinkTime)

  val headers_9 = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "cache-control" -> "max-age=0",
    "content-type" -> "multipart/form-data; boundary=----WebKitFormBoundaryuOBA1cG2BApZkrL6",
    "origin" -> "https://sscs-cor.aat.platform.hmcts.net",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "same-origin",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1")

  val login =
    exec(http("TX01_SSCS_COR_LoginPage")
      .post(idamUrl + "/login?redirect_uri=https%3A%2F%2Fsscs-cor.aat.platform.hmcts.net%2Fsign-in&client_id=sscs_cor&response_type=code&state=${reference}")
      .formParam("username", "${email}")
      .formParam("password", "Pass19word")
      .formParam("_csrf", "${csrf}")
      .formParam("save", "Sign in")
      .formParam("selfRegistrationEnabled", "true")
      .check(status.is(200))
     // .check(CsrfCheck.save)
      //.check(regex("Enter the postcode for the appeal"))
    )
      .pause(tyaThinkTime)

  val enterPostcode = exec(http("TX02_SSCS_TYA_EnterPostcode")
    .post("/assign-case")
    .formParam("_csrf", "${csrf}")
    .formParam("postcode", "${postcode}")
    .formParam("assign-case", "Continue")
    .check(status.is(200))
  //  .check(CsrfCheck.save)
    )
    .pause(tyaThinkTime)

  val yourDetails =
    exec(http("TX03_SSCS_TYA_YourDetails")
      .get("/task-list")
      .check(status.is(200)))
      .pause(tyaThinkTime)

  val getAdditionalEvidence =
    exec(http("TX04_SSCS_TYA_Addnl_Evidence")
      .get("/additional-evidence")
      .check(CsrfCheck.save)
      .check(status.is(200)))

      .pause(tyaThinkTime)
  val AddnlEviUpload =
    exec(http("TX04_SSCS_TYA_Evidence_Upload")
      .post("/additional-evidence")
      .formParam("additional-evidence-option", "upload")
      .formParam("_csrf", "${csrf}")
      .formParam("continue", "")

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

      .pause(tyaThinkTime)

  val AddnlEviUpload_Post =
    exec(http("TX04_SSCS_TYA_Evidence_Upload1")
     // .post("/additional-evidence/upload?_csrf=${csrf}")
      .post("${uploadurl}")
.headers(headers_9)
     // .check(CsrfCheck.save)
     .check(status.is(200))
      .check(css("#additional-evidence-form", "action").saveAs("uploadurl1"))
      )
      .pause(tyaThinkTime)

  val AddnlEviUploadFile =
    exec(http("request_25")
      .post("${uploadurl1}")
      .body(RawFileBody("RecordedSimulationlatestupdate_0082_request.txt"))
      //.check(CsrfCheck.save)
      .check(status.is(200)))
      .pause(tyaThinkTime)

  val Hearing =
    exec(http("request_31")
      .get("/hearing")
      .check(status.is(200)))
      .pause(tyaThinkTime)

  val logout =
  exec(http("request_34")
    .get("/sign-out")
      .check(status.is(200)))
      .pause(tyaThinkTime)


}