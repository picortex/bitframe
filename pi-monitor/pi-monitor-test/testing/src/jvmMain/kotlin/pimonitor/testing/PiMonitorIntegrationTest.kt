package pimonitor.testing

import bitframe.service.client.config.KtorClientConfiguration
import bitframe.service.client.config.ServiceConfig
import bitframe.testing.APP_ID
import bitframe.testing.TestMode
import cache.MockCache

actual open class PiMonitorIntegrationTest : PiMonitorContainerTest() {
    actual val config: KtorClientConfiguration
        get() = when (mode) {
            TestMode.DEV -> KtorClientConfiguration(
                url = "http://localhost:8080",
                appId = APP_ID,
                cache = MockCache()
            )
            TestMode.CI -> KtorClientConfiguration(
                url = urlUnderTest,
                appId = APP_ID,
                cache = MockCache()
            )
            TestMode.CD -> TODO()
        }
}