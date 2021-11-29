package integration.authentication

import bitframe.service.client.config.KtorClientConfiguration
import cache.MockCache
import expect.expect
import bitframe.PiMonitorServiceKtor
import bitframe.PiMonitorServiceStub
import bitframe.PiMonitorServiceStubConfig
import bitframe.authentication.signup.SignUpServiceTest
import kotlin.test.Test

class SignUpServiceIntegrationTest : SignUpServiceTest() {

    override val service: PiMonitorService by lazy {
        val cache = MockCache()
        when (val cfg = config) {
            is KtorClientConfiguration -> PiMonitorServiceKtor(cfg, cache)
            else -> PiMonitorServiceStub(PiMonitorServiceStubConfig(cfg.appId), cache)
        }
    }

    @Test
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }
}