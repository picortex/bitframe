package bitframe.testing

import bitframe.client.ServiceConfig

expect open class IntegrationTest() : ContainerTest {
    val config: ServiceConfig
}