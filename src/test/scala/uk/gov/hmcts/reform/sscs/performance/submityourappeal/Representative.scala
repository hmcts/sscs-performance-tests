package uk.gov.hmcts.reform.sscs.performance.submityourappeal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import uk.gov.hmcts.reform.cmc.performance.simulations.checks.{CsrfCheck, CurrentPageCheck}
//import uk.gov.hmcts.reform.idam.User
import uk.gov.hmcts.reform.sscs.performance.utils._

object Representative{

  val thinktime = Environment.thinkTime
  
    val feeder = csv("TYA_Details.csv").circular
   
  //def logIn(user: User)(implicit postHeaders: Map[String, String]): ChainBuilder = {

  val representative=
  exec(http("TX18_SSCS_Representative")
    .post("/representative")
    .formParam("hasRepresentative", "yes")
    .check(regex("Enter their details")))
    .pause(thinktime)



val repDetails=
  exec(http("TX19_SSCS_RepresentativeDetails")
    .post("/representative-details")
    .formParam("name.first", "sdsdsd")
    .formParam("name.last", "dfdfdfd")
    .formParam("name.organisation", "sdsdsd")
    .formParam("addressLine1", "balfour road")
    .formParam("addressLine2", "Hibernia Gardens")
    .formParam("townCity", "Hounslow")
    .formParam("county", "middlesex")
    .formParam("postCode", "TW3 1JX")
    .formParam("phoneNumber", "${repphonenumber}")
    .formParam("emailAddress", "${repemail}"+"@perftest.uk.gov")
    .check(regex("Your reasons for appealing")))
    .pause(thinktime)








}
