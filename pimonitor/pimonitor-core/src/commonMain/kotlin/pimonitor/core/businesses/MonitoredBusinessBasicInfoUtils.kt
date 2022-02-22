@file:JsExport

package pimonitor.core.businesses

import kotlin.js.JsExport

fun MonitoredBusinessBasicInfo.toMonitoredBusinessRef(name: String) = MonitoredBusinessRef(
    uid = uid,
    spaceId = spaceId,
    owningSpaceId = owningSpaceId,
    address = address,
    name = name
)