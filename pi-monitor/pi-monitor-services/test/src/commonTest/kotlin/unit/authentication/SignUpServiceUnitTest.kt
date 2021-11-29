package unit.authentication

import expect.expect
import bitframe.PiMonitorServiceStub
import bitframe.PiMonitorServiceStubConfig
import bitframe.authentication.signup.SignUpServiceTest
import pimonitor.testing.annotations.Lifecycle
import pimonitor.testing.annotations.TestInstance
import pimonitor.testing.annotations.Testcontainers
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