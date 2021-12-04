package bitframe.testing

import bitframe.service.client.config.KtorClientConfiguration
import bitframe.service.client.config.ServiceConfig
import cache.MockCache

actual open class IntegrationTest : ContainerTest() {
    actual val config: ServiceConfig
        get() = configuration

    val configuration
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