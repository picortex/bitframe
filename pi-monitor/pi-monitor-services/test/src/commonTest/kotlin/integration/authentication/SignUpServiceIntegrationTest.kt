package integration.authentication

import bitframe.service.config.KtorClientConfiguration
import expect.expect
import pimonitor.PiMonitorService
import pimonitor.PiMonitorServiceKtor
import pimonitor.PiMonitorServiceStub
import pimonitor.StubServiceConfig
import pimonitor.authentication.signup.SignUpServiceTest
import testing.annotations.Lifecycle
import testing.annotations.TestInstance
import testing.annotations.Testcontainers
import kotlin.test.Test

class SignUpServiceIntegrationTest : SignUpServiceTest() {

    override val service: PiMonitorService by lazy {
        when (val cfg = config) {
            is KtorClientConfiguration -> PiMonitorServiceKtor(cfg)
            else -> PiMonitorServiceStub(StubServiceConfig(cfg.appId))
        }
    }

    @Test
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }
}