package unit.signup

import core.signup.SignUpViewModelTest
import pimonitor.PiMonitorService
import pimonitor.PiMonitorServiceStub
import pimonitor.StubServiceConfig
import kotlin.test.Test

class SignUpViewModelUnitTest : SignUpViewModelTest() {

    override val service: PiMonitorService by lazy {
        PiMonitorServiceStub(StubServiceConfig(config.appId))
    }

    @Test
    fun should_pass() {

    }
}