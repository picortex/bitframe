package pimonitor.core.businesses

fun MonitoredBusinessBasicInfo.toMonitoredBusinessRef() = MonitoredBusinessRef(
    uid = uid,
    address = address,
    name = name
)