package bitframe.testing

import bitframe.client.KtorServiceConfig
import bitframe.client.ServiceConfig
import cache.MockCache

actual open class IntegrationTest : ContainerTest() {
    actual val config: ServiceConfig
        get() = configuration

    val configuration
        get() = when (mode) {
            TestMode.DEV -> KtorServiceConfig(
                url = "http://localhost:8080",
                appId = APP_ID,
                cache = MockCache()
            )
            TestMode.CI -> KtorServiceConfig(
                url = urlUnderTest,
                appId = APP_ID,
                cache = MockCache()
            )
            TestMode.CD -> TODO()
        }
}