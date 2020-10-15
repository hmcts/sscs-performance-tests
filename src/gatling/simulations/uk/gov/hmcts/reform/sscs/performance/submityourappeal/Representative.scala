package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.sscs.performance.simulations.checks.CsrfCheck
import uk.gov.hmcts.reform.sscs.performance.simulations.checks.CsrfCheck.{csrfParameter, csrfTemplate}
//import uk.gov.hmcts.reform.sscs.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object Representative{

  val thinktime = Environment.thinkTime
  
    //val feeder = csv("TYA_Details.csv").circular
   
  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {

  // =======================================================================================
  // Indicate whether you have a representative
  // =======================================================================================

  val representative=
  exec(http("TX18_SSCS_Representative")
    .post("/representative")
    .formParam("hasRepresentative", "no")
  //  .formParam(csrfParameter, csrfTemplate)
   // .check(regex("Enter their details"))
   // .check(CsrfCheck.save)
  )
    .pause(thinktime)

  // =======================================================================================
  // Provide your representative's details
  // =======================================================================================

  val repDetails=
  exec(http("TX19_SSCS_RepresentativeDetails")
    .post("/representative-details")
      .formParam("name.title","Mr")
    .formParam("name.first", "PerfRepFirst")
    .formParam("name.last", "PerfRepLast")
    .formParam("name.organisation", "PerfOrganization")
    .formParam("addressLine1", "PerfRep Street")
    .formParam("addressLine2", "Perf Gardens")
    .formParam("townCity", "PerfCity")
    .formParam("county", "PerfCounty")
    .formParam("postCode", "TW3 1JX")
    .formParam("phoneNumber", "07540612435")
    .formParam("emailAddress", "repemail@perftest.uk.gov")
    .formParam(csrfParameter, csrfTemplate)
    .check(regex("Your reasons for appealing"))
    .check(CsrfCheck.save)
  )
    .pause(thinktime)








}
