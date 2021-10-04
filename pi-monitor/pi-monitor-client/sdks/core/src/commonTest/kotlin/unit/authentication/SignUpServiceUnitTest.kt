package unit.authentication

import bitframe.authentication.TestClientConfiguration
import bitframe.service.config.KtorClientConfiguration
import core.authentication.SignUpServiceTest
import expect.expect
import pimonitor.PiMonitorService
import pimonitor.ktor.PiMonitorServiceKtor
import pimonitor.test.PiMonitorServiceTest
import testing.annotations.Lifecycle
import testing.annotations.TestInstance
import testing.annotations.Testcontainers
import kotlin.test.Test

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
class SignUpServiceUnitTest : SignUpServiceTest() {

    override val service: PiMonitorService by lazy {
        PiMonitorServiceTest(TestClientConfiguration.from(config, "test-app"))
    }

    @Test
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }
}