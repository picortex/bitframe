package pimonitor.authentication.signup.exports

import pimonitor.authentication.signup.IndividualRegistrationParams
import pimonitor.authentication.signup.MonitorBusinessParams

fun IndividualParams.toIndividualRegistrationParams() = IndividualRegistrationParams(name, email, password)
fun OrganisationParams.toMonitorBusinessParams() = MonitorBusinessParams(name, email)