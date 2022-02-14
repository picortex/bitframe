package integration.ktor.utils

import bitframe.client.BitframeApi
import bitframe.client.BitframeApiKtorConfig
import bitframe.testing.ContainerTest
import cache.MockCache

open class IntegrationTest : ContainerTest() {
    companion object {
        val DEV_KTOR_CLIENT_CONFIG by lazy {
            BitframeApiKtorConfig(
                url = urlUnderTest,
                appId = "<test>",
                cache = MockCache()
            )
        }

        val API: BitframeApi by lazy {
            BitframeTestKtorApi(DEV_KTOR_CLIENT_CONFIG)
        }
    }
}