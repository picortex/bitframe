package pimonitor.testing

import bitframe.client.PiMonitorService
import bitframe.client.PiMonitorServiceKtor
import bitframe.client.PiMonitorServiceKtorConfig
import bitframe.testing.APP_ID
import bitframe.testing.TestMode
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