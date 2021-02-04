package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.sscs.performance.simulations.checks.CsrfCheck
import uk.gov.hmcts.reform.sscs.performance.simulations.checks.CsrfCheck.{csrfParameter, csrfTemplate}
import uk.gov.hmcts.reform.sscs.performance.utils.Environment._
//import uk.gov.hmcts.reform.sscs.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object StartAppealPage{

  val thinktime = Environment.thinkTime
  val idamUrl = Environment.IDAMURL
  val sscsSYAURL = Environment.sscsSYAURL
  val env = Environment.env

  val sscsfeeder = csv("sscs_details.csv").circular
  val loginfeeder = csv("IdamUsers.csv").circular
  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {
  //1

  // =======================================================================================
  // Have a look at the entry page
  // =======================================================================================

  val environment =
    exec{
      session =>
        println("This is the environment: " + env)
        session
    }

  val entry = feed(sscsfeeder).feed(loginfeeder)
    .exec(http("TX05_SSCS_Entry")
      .get("/")
      .headers(SSCSSYAHeaders.headers_0)
      .check(status.in(200,302))
      .check(regex("Which benefit is your appeal about?"))
      //.check(CsrfCheck.save)
   )
      .pause(thinktime)

  // =======================================================================================
  // Indicate which benefit type your appeal is about
  // =======================================================================================

  val benefitType =
    exec(http("TX06_SSCS_BenefitType")
      .post("/benefit-type")
      .headers(SSCSSYAHeaders.headers_2)
      .formParam("benefitType", "Personal Independence Payment (PIP)") //Employment and Support Allowance (ESA) Personal Independence Payment (PIP)
      .check(status.in(200,302))
      //.formParam(csrfParameter, csrfTemplate)
      .check(regex("What language do you want us to use when we contact you?"))
      //.check(CsrfCheck.save)
  )
    .pause(thinktime)

  // =======================================================================================
  // Indicate which language you prefer
  // =======================================================================================

  val languagePreference =
    exec(http("TX07_SSCS_LanguagePreference")
      .post("/language-preference")
      .headers(SSCSSYAHeaders.headers_2)
      .formParam("languagePreferenceWelsh", "no")
      .check(status.in(200,302))
      //.formParam(csrfParameter, csrfTemplate)
      //.check(CsrfCheck.save)
    )
      .pause(thinktime)

  // =======================================================================================
  // Provide your postcode
  // =======================================================================================

  val postCodeCheck =
    exec(http("TX08_SSCS_PostCodeCheck")
      .post("/postcode-check")
      .headers(SSCSSYAHeaders.headers_2)
      .formParam("postcode", "TS1 1ST")
      .check(status.in(200,302))
      //.formParam(csrfParameter, csrfTemplate)
      .check(regex("Your appeal will be decided by an independent tribunal"))
      //.check(CsrfCheck.save)
  )
    .pause(thinktime)

  // =======================================================================================
  // Indicate whether you are an appointee
  // =======================================================================================

  val areYouAnAppointee =
    exec(http("TX09_SSCS_AreYouAnAppointee")
      .post("/are-you-an-appointee")
      .formParam("isAppointee", "no")
      .check(status.in(200,302))
      //.formParam(csrfParameter, csrfTemplate)
      //.check(regex("Your hearing - Appeal a benefit decision"))
      //.check(CsrfCheck.save)
    )
    .pause(thinktime)

//4

  // =======================================================================================
  // Indicate whether you want to save the appeal later (before login)
  // =======================================================================================


  val independenceBeforeLogin=
    exec(http("TX10_SSCS_IndependenceBeforeLogin")
      .post("/independence")
      .headers(SSCSSYAHeaders.headers_2)
      .check(status.in(200,302))
      //.check(CsrfCheck.save)
      .check(regex("Do you want to be able to save this appeal later?"))
  )
    .pause(thinktime)

  // =======================================================================================
  // Indicate whether you want to save the appeal later (after login)
  // =======================================================================================


  val independenceAfterLogin=
    exec(http("TX11_SSCS_IndependenceAfterLogin")
      .post("/independence")
      .check(status.in(200,302))
      //.check(CsrfCheck.save)
      //.check(regex("Do you want to be able to save this appeal later?"))
    )
      .pause(thinktime)

  // =======================================================================================
  // Create an account
  // =======================================================================================

  val saveLater=
    exec(http("TX12_SSCS_CreateAccount")
      .post("/create-account")
      .headers(SSCSSYAHeaders.headers_2)
      .formParam("createAccount", "no")
      .check(CsrfCheck.save)
      .check(regex("state=(.*)&scope=").saveAs("stateId"))
      .check(status.in(200,302)))
      .pause(thinktime)

  //continue to save later

  // =======================================================================================
  // Log in
  // =======================================================================================

  val login=
    exec(http("TX13_SSCS_Login")
      .post(idamUrl + "/login?client_id=sscs&redirect_uri=" + sscsSYAURL + "%2Fauthenticated&response_type=code&state=${stateId}")
      //.headers(headers_196)
      .formParam("username", "${idamUser}") //${email}@mailinator.com
      .formParam("password", "Testing123") //Pass19word
      .formParam("save", "Sign in")
      .formParam("selfRegistrationEnabled", "true")
      .formParam(csrfParameter, csrfTemplate)
      .check(status.in(200,302)))
      .pause(thinktime)

  // =======================================================================================
  // Check your appeal
  // =======================================================================================


  val checkYourAppeal1=
    exec(http("TX14_SSCS_CheckYourAppeal")
      .post("/check-your-appeal")
      .check(status.in(200,302))
      //.check(CsrfCheck.save)
      //.check(regex("Do you want to be able to save this appeal later?"))
    )
      .pause(thinktime)

}


