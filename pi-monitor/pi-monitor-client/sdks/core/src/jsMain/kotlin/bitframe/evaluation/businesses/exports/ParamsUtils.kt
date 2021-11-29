package bitframe.evaluation.businesses.exports

import bitframe.monitored.CreateMonitoredBusinessParams

fun CreateBusinessFormParams.toParams() = CreateMonitoredBusinessParams(
    businessName, contactName, contactEmail
)