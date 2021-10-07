package integration.signup

import bitframe.service.config.KtorClientConfiguration
import core.signup.SignUpViewModelTest
import pimonitor.PiMonitorService
import pimonitor.PiMonitorServiceKtor
import pimonitor.PiMonitorServiceStub
import pimonitor.StubServiceConfig
import kotlin.test.Test

class SignUpViewModelIntegrationTest : SignUpViewModelTest() {

    override val service: PiMonitorService by lazy {
        when (val cfg = config) {
            is KtorClientConfiguration -> PiMonitorServiceKtor(cfg)
            else -> PiMonitorServiceStub(StubServiceConfig(cfg.appId))
        }
    }
    
    @Test
    fun should_pass() {
    }
}