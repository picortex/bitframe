package pimonitor.authentication.signup.exports

import pimonitor.authentication.signup.IndividualRegistrationParams
import pimonitor.authentication.signup.MonitorBusinessParams
import pimonitor.monitors.SignUpParams

fun RegisterIndividualParams.toIndividualRegistrationParams() = IndividualRegistrationParams(name, email, password)
fun RegisterOrganisationParams.toMonitorBusinessParams() = MonitorBusinessParams(name, email)

fun RegisterIndividualParams.toSignUpParams() = SignUpParams.Individual(name, email, password)

fun RegisterBusinessParams.toSignUpParams() = SignUpParams.Business(businessName, individualName, individualEmail, password)