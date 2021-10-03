package testing

import bitframe.service.config.KtorClientConfiguration
import bitframe.service.config.ServiceConfig

actual open class IntegrationTest : ContainerTest() {
    actual val config: ServiceConfig
        get() = configuration

    val configuration
        get() = when (mode) {
            TestMode.DEV -> KtorClientConfiguration(
                url = "http://localhost:8080",
                appId = "dev-app-1"
            )
            TestMode.CI -> KtorClientConfiguration(
                url = urlUnderTest,
                appId = "ci-app-1"
            )
            TestMode.CD -> TODO()
        }
}