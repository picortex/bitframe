package integration.ktor.utils

import bitframe.api.BitframeService
import bitframe.api.BitframeServiceKtorConfig
import bitframe.testing.ContainerTest
import cache.MockCache

open class IntegrationTest : ContainerTest() {
    companion object {
        val DEV_KTOR_CLIENT_CONFIG by lazy {
            BitframeServiceKtorConfig(
                url = urlUnderTest,
                appId = "<test>",
                cache = MockCache()
            )
        }

        val service: BitframeService by lazy {
            BitframeTestKtorService(DEV_KTOR_CLIENT_CONFIG)
        }
    }
}