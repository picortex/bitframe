package testing.containers

import bitframe.service.config.KtorClientConfiguration
import testing.containers.ContainerTest.Companion.mode
import testing.containers.ContainerTest.Companion.urlUnderTest
import testing.containers.ContainerTest.TestMode.*

open class IntegrationTest : ContainerTest {
    val config
        get() = when (mode) {
            DEV -> KtorClientConfiguration(
                url = "http://localhost:8080",
                appId = "dev-app-1"
            )
            CI -> KtorClientConfiguration(
                url = urlUnderTest,
                appId = "ci-app-1"
            )
            CD -> TODO()
        }
}