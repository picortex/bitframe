package pimonitor.evaluation.businesses.exports

import pimonitor.monitored.CreateMonitoredBusinessParams

fun CreateBusinessFormParams.toParams() = CreateMonitoredBusinessParams(
    businessName, contactName, contactEmail
)