package pimonitor.api.businesses

import pimonitor.monitored.CreateMonitoredBusinessParams
import validation.required

fun RawCreateBusinessFormParams.toParams() = CreateMonitoredBusinessParams(
    businessName = required(::businessName),
    contactName = required(::contactName),
    contactEmail = required(::contactEmail)
)