package bitframe.testing

import bitframe.service.client.config.ServiceConfig
import bitframe.testing.ContainerTest
import cache.MockCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import bitframe.testing.pimonitor.APP_ID

actual open class IntegrationTest : ContainerTest() {
    actual val config: ServiceConfig = ServiceConfig(
        appId = APP_ID,
        cache = MockCache(),
        scope = CoroutineScope(SupervisorJob())
    )
}