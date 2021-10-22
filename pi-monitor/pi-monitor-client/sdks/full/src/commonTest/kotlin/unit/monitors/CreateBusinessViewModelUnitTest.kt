package unit.monitors

import core.monitors.CreateBusinessViewModelTest
import pimonitor.PiMonitorService
import pimonitor.PiMonitorServiceStub
import pimonitor.StubServiceConfig
import kotlin.test.Test

class CreateBusinessViewModelUnitTest : CreateBusinessViewModelTest() {
    override val service: PiMonitorService by lazy {
        PiMonitorServiceStub(StubServiceConfig(config.appId))
    }

    @Test
    fun should_pass() {

    }
}