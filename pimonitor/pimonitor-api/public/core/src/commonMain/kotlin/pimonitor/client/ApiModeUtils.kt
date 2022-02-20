package pimonitor.client

import bitframe.client.configurators.ApiMode
import bitframe.client.toKtorApiConfig
import bitframe.client.toMockApiConfig

fun ApiMode.toPiMonitorApi(): PiMonitorApi = when (this) {
    is ApiMode.Live -> PiMonitorApiKtor(toKtorApiConfig())
    is ApiMode.Mock -> PiMonitorApiMock(toMockApiConfig())
}