package pimonitor.testing

import pimonitor.api.PiMonitorServiceKtor
import bitframe.testing.APP_ID
import cache.MockCache
import pimonitor.api.PiMonitorService
import pimonitor.api.PiMonitorServiceKtorConfig

open class PiMonitorIntegrationTest : PiMonitorContainerTest() {
    val piMonitorService: PiMonitorService by lazy {
        val config = PiMonitorServiceKtorConfig(
            url = urlUnderTest,
            appId = APP_ID,
            cache = MockCache()
        )
        PiMonitorServiceKtor(config)
    }
}