package unit.signup

import core.signup.SignUpViewModelTest
import pimonitor.PiMonitorServiceStub
import pimonitor.PiMonitorServiceStubConfig
import kotlin.test.Test

class SignUpViewModelUnitTest : SignUpViewModelTest() {

    override val service: PiMonitorService by lazy {
        PiMonitorServiceStub(PiMonitorServiceStubConfig(config.appId))
    }

    @Test
    fun should_pass() {

    }
}