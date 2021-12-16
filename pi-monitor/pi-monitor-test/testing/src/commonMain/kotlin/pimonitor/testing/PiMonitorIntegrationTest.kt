package pimonitor.testing

import pimonitor.client.PiMonitorServiceKtor
import bitframe.testing.APP_ID
import cache.MockCache

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