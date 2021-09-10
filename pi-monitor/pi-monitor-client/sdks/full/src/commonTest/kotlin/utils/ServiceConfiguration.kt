package utils

import bitframe.authentication.TestClientConfiguration
import pimonitor.PiMonitorService
import pimonitor.test.PiMonitorServiceTestImpl

private val DEFAULT_TEST_CLIENT_CONFIG = TestClientConfiguration(
    appId = "test-app-default"
)

private val DELAYING_TEST_CLIENT_CONFIG = TestClientConfiguration(
    appId = "test-app-delayer",
    simulationTime = 3000
)

private val TEST_SERVICE: PiMonitorService = PiMonitorServiceTestImpl(
    DEFAULT_TEST_CLIENT_CONFIG
//    DELAYING_TEST_CLIENT_CONFIG
)

val SERVICE_UNDER_TEST = TEST_SERVICE
