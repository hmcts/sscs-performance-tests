package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.sscs.performance.simulations.checks.CsrfCheck
import uk.gov.hmcts.reform.sscs.performance.simulations.checks.CsrfCheck.{csrfParameter, csrfTemplate}
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

  val entry = //feed(sscsfeeder).feed(loginfeeder)
    exec(http("SSCS_010_Entry")
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
    exec(http("SSCS_020_BenefitType")
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
    exec(http("SSCS_030_LanguagePreference")
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
    exec(http("SSCS_040_PostCodeCheck")
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
    exec(http("SSCS_130_AreYouAnAppointee")
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
    exec(http("SSCS_050_IndependenceBeforeLogin")
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
    exec(http("SSCS_090_IndependenceAfterLogin")
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
    exec(http("SSCS_060_CreateAccount")
      .post("/create-account")
      .headers(SSCSSYAHeaders.headers_2)
      .formParam("createAccount", "yes")
      .check(CsrfCheck.save)
      .check(regex("state=(.*)&scope=").saveAs("stateId"))
      .check(status.in(200,302)))
      .pause(thinktime)

  //continue to save later

  // =======================================================================================
  // Log in
  // =======================================================================================

val login1 =
    exec(http("SSCS_070_Login1")
      .get(idamUrl + "/login?client_id=sscs&redirect_uri=https%3A%2F%2Fbenefit-appeal.perftest.platform.hmcts.net%2Fauthenticated&ui_locales=en&response_type=code&state=${stateId}")
      .headers(SSCSSYAHeaders.headers_17)
      .check(status.in(200,302)))
  .pause(thinktime)

val login2 =
      exec(http("SSCS_080_Login2")
      .post(idamUrl + "/login?client_id=sscs&redirect_uri=https%3A%2F%2Fbenefit-appeal.perftest.platform.hmcts.net%2Fauthenticated&ui_locales=en&response_type=code&state=${stateId}")
      .headers(SSCSSYAHeaders.headers_18)
      .formParam("username", "${idamUser}") //${email}@mailinator.com
      .formParam("password", "Pass19word")
      .formParam("save", "Sign in")
      .formParam("selfRegistrationEnabled", "true")
      .formParam(csrfParameter, csrfTemplate)
      .check(status.in(200,302)))
      .exitHereIfFailed
      .pause(thinktime)

  // =======================================================================================
  // Check your appeal
  // =======================================================================================


  /*val checkYourAppeal1=
    exec(http("TX14_SSCS_CheckYourAppeal")
      .post("/check-your-appeal")
      .check(status.in(200,302))
      //.check(CsrfCheck.save)
      //.check(regex("Do you want to be able to save this appeal later?"))
    )
      .pause(thinktime)*/

}


