package acceptance.utils

import bitframe.service.config.KtorClientConfiguration
import testing.ContainerTest
import testing.TestMode

open class IntegrationTest : ContainerTest() {
    val config
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