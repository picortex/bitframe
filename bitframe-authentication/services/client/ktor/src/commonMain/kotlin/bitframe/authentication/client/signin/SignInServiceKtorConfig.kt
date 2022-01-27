package bitframe.authentication.client.signin

import bitframe.authentication.client.SigningServiceConfig
import bitframe.authentication.signin.Session
import events.EventBus
import bitframe.service.client.config.KtorClientConfiguration
import cache.Cache
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.Live
import logging.Logger
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmSynthetic

interface SignInServiceKtorConfig : SigningServiceConfig, KtorClientConfiguration {
    companion object {
        @JvmSynthetic
        operator fun invoke(
            url: String,
            appId: String,
            cache: Cache,
            session: Live<Session> = SigningServiceConfig.DEFAULT_SIGN_IN_SESSION,
            bus: EventBus = KtorClientConfiguration.DEFAULT_BUS,
            logger: Logger = KtorClientConfiguration.DEFAULT_LOGGER,
            http: HttpClient = KtorClientConfiguration.DEFAULT_HTTP_CLIENT,
            json: Json = KtorClientConfiguration.DEFAULT_JSON,
            scope: CoroutineScope = KtorClientConfiguration.DEFAULT_SCOPE,
        ): SignInServiceKtorConfig = object : SignInServiceKtorConfig,
            KtorClientConfiguration by KtorClientConfiguration(url, appId, cache, bus, logger, http, json, scope) {
            override val signInSession: Live<Session> = session
        }

        @JvmSynthetic
        @JvmOverloads
        fun create(
            url: String,
            appId: String,
            cache: Cache,
            session: Live<Session> = SigningServiceConfig.DEFAULT_SIGN_IN_SESSION,
            bus: EventBus = KtorClientConfiguration.DEFAULT_BUS,
            logger: Logger = KtorClientConfiguration.DEFAULT_LOGGER,
            http: HttpClient = KtorClientConfiguration.DEFAULT_HTTP_CLIENT,
            json: Json = KtorClientConfiguration.DEFAULT_JSON,
            scope: CoroutineScope = KtorClientConfiguration.DEFAULT_SCOPE,
        ) = invoke(url, appId, cache, session, bus, logger, http, json, scope)
    }
}