package pimonitor.testing

import bitframe.service.client.config.ServiceConfig
import bitframe.testing.APP_ID
import cache.MockCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

actual open class PiMonitorIntegrationTest : PiMonitorContainerTest() {
    actual val config: ServiceConfig = ServiceConfig(
        appId = APP_ID,
        cache = MockCache(),
        scope = CoroutineScope(SupervisorJob())
    )
}