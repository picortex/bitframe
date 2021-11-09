package integration

import bitframe.authentication.client.signin.SignInServiceKtorConfig
import bitframe.service.client.config.KtorClientConfiguration
import cache.MockCache
import io.ktor.client.engine.*

val DEV_KTOR_CLIENT_CONFIG = SignInServiceKtorConfig(
    url = "http://localhost:8080",
    appId = "<test>",
    cache = MockCache()
)

val CONFIG_UNDER_TEST = DEV_KTOR_CLIENT_CONFIG