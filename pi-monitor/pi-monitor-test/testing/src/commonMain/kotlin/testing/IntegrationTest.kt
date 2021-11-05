package testing

import bitframe.service.client.config.ServiceConfig

expect open class IntegrationTest() : ContainerTest {
    val config: ServiceConfig
}