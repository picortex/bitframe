package testing

import bitframe.service.config.ServiceConfig

expect open class IntegrationTest() : ContainerTest {
    val config: ServiceConfig
}