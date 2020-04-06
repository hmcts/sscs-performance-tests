package uk.gov.hmcts.reform.sscs.performance.simulations

import io.gatling.core.Predef.exec
import uk.gov.hmcts.reform.sscs.performance.usercreation.UserCreation
//import uk.gov.hmcts.reform.sscs.performance.processes._
//import uk.gov.hmcts.reform.sscs.performance.simulations.lifecycle.SimulationHooks
//import uk.gov.hmcts.reform.idam.{User,LoginPage}

object  UserCreateSimulation {

  def createUser=
    exec(
      //UserCreation.createUsers
      UserCreation.emailNotification
    )

}
