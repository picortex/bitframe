package integration.signup

import bitframe.service.client.config.KtorClientConfiguration
import cache.MockCache
import core.signup.SignUpViewModelTest
import pimonitor.PiMonitorServiceKtor
import pimonitor.PiMonitorServiceStub
import pimonitor.PiMonitorServiceStubConfig
import kotlin.test.Test

class SignUpViewModelIntegrationTest : SignUpViewModelTest() {

    override val service: PiMonitorService by lazy {
        val cache = MockCache()
        when (val cfg = config) {
            is KtorClientConfiguration -> PiMonitorServiceKtor(cfg, cache)
            else -> PiMonitorServiceStub(PiMonitorServiceStubConfig(cfg.appId), cache)
        }
    }

    @Test
    fun should_pass() {
    }
}