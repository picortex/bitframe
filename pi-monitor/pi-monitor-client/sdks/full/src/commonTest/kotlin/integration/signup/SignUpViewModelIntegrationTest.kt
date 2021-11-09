//package integration.signup
//
//import bitframe.service.client.config.KtorClientConfiguration
//import cache.MockCache
//import core.signup.SignUpViewModelTest
//import pimonitor.client.PiMonitorServiceKtor
//import pimonitor.PiMonitorServiceStub
//import pimonitor.PiMonitorServiceStubConfig
//import pimonitor.client.PiMonitorService
//import pimonitor.client.PiMonitorServiceKtor
//import kotlin.test.Test
//
//class SignUpViewModelIntegrationTest : SignUpViewModelTest() {
//
//    override val service: PiMonitorService by lazy {
//        val cache = MockCache()
////        PiMonitorServiceStub()
////        when (val cfg = config) {
////            is KtorClientConfiguration -> PiMonitorServiceKtor(cfg, cache)
////            else -> PiMonitorServiceStub(PiMonitorServiceStubConfig(cfg.appId), cache)
////        }
//        TODO()
//    }
//
//    //
//    @Test
//    fun should_pass() {
//    }
//}