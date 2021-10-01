package integration.ktor.utils

import bitframe.authentication.KtorClientConfiguration
import testing.ContainerTest

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