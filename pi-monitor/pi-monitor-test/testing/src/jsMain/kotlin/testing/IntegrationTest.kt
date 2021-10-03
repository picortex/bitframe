package testing

import bitframe.service.config.ServiceConfig

actual open class IntegrationTest : ContainerTest() {
    actual val config: ServiceConfig = ServiceConfig.DEFAULT
}