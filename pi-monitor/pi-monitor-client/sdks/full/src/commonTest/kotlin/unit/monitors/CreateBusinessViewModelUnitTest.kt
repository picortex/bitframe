package unit.monitors

import core.monitors.CreateBusinessViewModelTest
import pimonitor.PiMonitorServiceStub
import pimonitor.PiMonitorServiceStubConfig
import kotlin.test.Test

class CreateBusinessViewModelUnitTest : CreateBusinessViewModelTest() {
    override val service: PiMonitorService by lazy {
        PiMonitorServiceStub(PiMonitorServiceStubConfig(config.appId))
    }

    @Test
    fun should_pass() {

    }
}