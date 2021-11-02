package integration.signup

import bitframe.service.config.KtorClientConfiguration
import cache.MockCache
import core.signup.SignUpViewModelTest
import pimonitor.PiMonitorService
import pimonitor.PiMonitorServiceKtor
import pimonitor.PiMonitorServiceStub
import pimonitor.StubServiceConfig
import kotlin.test.Ignore
import kotlin.test.Test

class SignUpViewModelIntegrationTest : SignUpViewModelTest() {

    override val service: PiMonitorService by lazy {
        val cache = MockCache()
        when (val cfg = config) {
            is KtorClientConfiguration -> PiMonitorServiceKtor(cfg, cache)
            else -> PiMonitorServiceStub(StubServiceConfig(cfg.appId), cache)
        }
    }

    @Test
    fun should_pass() {
    }
}