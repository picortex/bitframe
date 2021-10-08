package testing

import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import testing.pimonitor.APP_ID

actual open class IntegrationTest : ContainerTest() {
    actual val config: ServiceConfig = ServiceConfig(
        appId = APP_ID,
        scope = CoroutineScope(SupervisorJob())
    )
}