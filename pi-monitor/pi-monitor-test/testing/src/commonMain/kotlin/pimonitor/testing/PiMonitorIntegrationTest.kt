package pimonitor.testing

import bitframe.service.client.config.ServiceConfig

expect open class PiMonitorIntegrationTest() : PiMonitorContainerTest {
    val config: ServiceConfig
}