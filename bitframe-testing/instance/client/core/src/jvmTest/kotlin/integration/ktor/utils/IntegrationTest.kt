package integration.ktor.utils

import bitframe.authentication.client.signin.SignInServiceKtorConfig
import bitframe.client.BitframeService
import bitframe.client.BitframeServiceKtorConfig
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