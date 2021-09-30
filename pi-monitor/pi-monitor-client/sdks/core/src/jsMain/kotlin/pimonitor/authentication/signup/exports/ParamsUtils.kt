package pimonitor.authentication.signup.exports

import pimonitor.MonitorBusinessParams
import pimonitor.authentication.signup.IndividualRegistrationParams

fun IndividualParams.toIndividualRegistrationParams() = IndividualRegistrationParams(name, email, password)
fun OrganisationParams.toMonitorBusinessParams() = MonitorBusinessParams(name, email)