package bitframe.testing

import bitframe.client.ServiceConfig
import cache.MockCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

actual open class IntegrationTest : ContainerTest() {
    actual val config: ServiceConfig = ServiceConfig(
        appId = APP_ID,
        cache = MockCache(),
        scope = CoroutineScope(SupervisorJob())
    )
}