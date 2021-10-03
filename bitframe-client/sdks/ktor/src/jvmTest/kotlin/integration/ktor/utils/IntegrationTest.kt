package integration.ktor.utils

import bitframe.service.config.KtorClientConfiguration
import testing.containers.ContainerTest

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