package pimonitor.evaluation.business.exports

import pimonitor.monitored.CreateMonitoredBusinessParams

fun CreateBusinessFormParams.toParams() = CreateMonitoredBusinessParams(
    businessName, contactName, contactEmail
)