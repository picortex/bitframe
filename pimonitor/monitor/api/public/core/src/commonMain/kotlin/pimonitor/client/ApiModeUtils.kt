package pimonitor.client

import bitframe.client.configurators.ApiMode
import bitframe.client.toApiConfigKtor
import pimonitor.client.config.toMonitorApiConfigMock

fun ApiMode.toPiMonitorApi(): MonitorApi = when (this) {
    is ApiMode.Live -> MonitorApiKtor(toApiConfigKtor())
    is ApiMode.Mock -> MonitorApiMock(toMonitorApiConfigMock())
}