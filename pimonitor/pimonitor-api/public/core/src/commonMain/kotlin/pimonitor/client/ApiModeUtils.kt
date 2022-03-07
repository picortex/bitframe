package pimonitor.client

import bitframe.client.configurators.ApiMode
import bitframe.client.toApiConfigKtor
import bitframe.client.toApiConfigMock

fun ApiMode.toPiMonitorApi(): PiMonitorApi = when (this) {
    is ApiMode.Live -> PiMonitorApiKtor(toApiConfigKtor())
    is ApiMode.Mock -> PiMonitorApiMock(toApiConfigMock())
}