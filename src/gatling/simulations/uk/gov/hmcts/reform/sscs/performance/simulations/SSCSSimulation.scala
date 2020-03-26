package uk.gov.hmcts.reform.sscs.performance.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef.{Proxy, http}
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmcts.reform.sscs.performance.utils.Environment

class SSCSSimulation extends Simulation
     {

       val httpProtocolTYA: HttpProtocolBuilder = http.proxy(Proxy("proxyout.reform.hmcts.net",8080))
         .baseUrl(Environment.SSCSCORURL)
         .headers(Environment.commonHeader)


       val httpProtocolSYA: HttpProtocolBuilder = http.proxy(Proxy("proxyout.reform.hmcts.net",8080))
         .baseUrl(Environment.sscsSYAURL)
         .headers(Environment.commonHeader)

       val httpProtocolUserCreation: HttpProtocolBuilder = http.proxy(Proxy("proxyout.reform.hmcts.net",8080))
         .baseUrl(Environment.sscsSYAURL)
         .headers(Environment.commonHeader)

       implicit val postHeaders: Map[String, String] = Map(
         "Origin" -> Environment.sscsSYAURL
       )
       val scenarioSSCSCOR = scenario("Create TYA Journey")
         .exec(CreateCORSimulation.createCORScenario)

       val scenarioSYA = scenario("Create SYA Journey")
         .exec(CreateSYASimulation.createSYAScenario)

       val scenarioUserCreation = scenario("Create User")
         .exec(UserCreateSimulation.createUser)


       /*setUp(
         scenarioSYA.inject(
           nothingFor(10),
           splitUsers(250) into (rampUsers(42) over (300)) separatedBy (0)).protocols(httpProtocolSYA),

         scenarioTYA.inject(
           nothingFor(10),
           splitUsers(30) into (rampUsers(2) over (30)) separatedBy (0)).protocols(httpProtocolTYA)

       ).maxDuration(5400)*/


      /* setUp(
         scenarioSYA.inject(
           nothingFor(10),
           rampUsers(1) during  (1)).protocols(httpProtocolSYA)
       )*/

       /*setUp(
         scenarioSSCSCOR.inject(
           nothingFor(10),
           rampUsers(1) during  (1)).protocols(httpProtocolTYA)
       )*/

       setUp(
         scenarioUserCreation.inject(
           nothingFor(10),
           rampUsers(1) during  (1)).protocols(httpProtocolUserCreation)
       )





       /* setUp(
          scenarioSYA.inject(
            nothingFor(10),
            splitUsers(250) into (rampUsers(42) over (600)) separatedBy (0)).protocols(httpProtocolSYA),

          scenarioTYA.inject(
            nothingFor(10),
            splitUsers(387) into (rampUsers(63) over (300)) separatedBy (0)).protocols(httpProtocolTYA)

        ).maxDuration(5400)*/

        /* setUp(scenario1
           .inject(
             rampUsers(1).over()
           rampUsers(1).over(10 seconds))
           .protocols(httpProtocol))
           .maxDuration(90 minutes)
           .assertions(
               global.responseTime.max.lt(5000),
               forAll.failedRequests.count.lt(20)
           )*/

      /* setUp(
         scenario1.inject(
           nothingFor(10 seconds),
           splitUsers(43) into (rampUsers(7) over (300)) separatedBy (0)).protocols(httpconf),

         scenario2.inject(
           nothingFor(10 seconds),
           splitUsers(387) into (rampUsers(63) over (300)) separatedBy (0)).protocols(httpconf)

       )*/



      /* setUp(
         scenarioSYA.inject(
           nothingFor(10),
           splitUsers(100) into (rampUsers(25) over (250)) separatedBy (0)).protocols(httpProtocolSYA)

       )*/

}