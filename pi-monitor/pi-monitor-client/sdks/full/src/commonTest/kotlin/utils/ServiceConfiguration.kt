package utils

import pimonitor.PiMonitorService
import pimonitor.PiMonitorServiceStub
import pimonitor.StubServiceConfig

private val DEFAULT_TEST_CLIENT_CONFIG = StubServiceConfig(
    appId = "test-app-default"
)

private val DELAYING_TEST_CLIENT_CONFIG = StubServiceConfig(
    appId = "test-app-delayer",
    simulationTime = 3000
)

private val TEST_SERVICE: PiMonitorService = PiMonitorServiceStub(
    DEFAULT_TEST_CLIENT_CONFIG
//    DELAYING_TEST_CLIENT_CONFIG
)

val SERVICE_UNDER_TEST = TEST_SERVICE
