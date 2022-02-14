package pimonitor.client.businesses

import pimonitor.core.monitored.CreateMonitoredBusinessParams
import validation.required

fun RawCreateBusinessFormParams.toParams() = CreateMonitoredBusinessParams(
    businessName = required(::businessName),
    contactName = required(::contactName),
    contactEmail = required(::contactEmail)
)