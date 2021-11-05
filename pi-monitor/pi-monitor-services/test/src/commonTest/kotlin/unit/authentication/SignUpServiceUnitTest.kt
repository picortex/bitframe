package unit.authentication

import expect.expect
import pimonitor.PiMonitorServiceStub
import pimonitor.PiMonitorServiceStubConfig
import pimonitor.authentication.signup.SignUpServiceTest
import testing.annotations.Lifecycle
import testing.annotations.TestInstance
import testing.annotations.Testcontainers
import kotlin.test.Test

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
class SignUpServiceUnitTest : SignUpServiceTest() {

    override val service: PiMonitorService by lazy {
        PiMonitorServiceStub(PiMonitorServiceStubConfig(config.appId))
    }

    @Test
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }
}