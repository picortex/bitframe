package testing

import bitframe.service.config.KtorClientConfiguration
import bitframe.service.config.ServiceConfig
import testing.pimonitor.APP_ID

actual open class IntegrationTest : ContainerTest() {
    actual val config: ServiceConfig
        get() = configuration

    val configuration
        get() = when (mode) {
            TestMode.DEV -> KtorClientConfiguration(
                url = "http://localhost:8080",
                appId = APP_ID
            )
            TestMode.CI -> KtorClientConfiguration(
                url = urlUnderTest,
                appId = APP_ID
            )
            TestMode.CD -> TODO()
        }
}