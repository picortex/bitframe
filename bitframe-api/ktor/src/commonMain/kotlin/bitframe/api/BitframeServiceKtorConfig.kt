package bitframe.api

import bitframe.authentication.client.SigningServiceConfig
import bitframe.authentication.client.signin.SignInServiceKtorConfig
import bitframe.authentication.signin.Session
import events.EventBus
import bitframe.service.client.config.KtorClientConfiguration
import bitframe.service.client.config.ServiceConfig
import cache.Cache
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.Live
import logging.Logger

interface BitframeServiceKtorConfig : BitframeServiceConfig, SignInServiceKtorConfig {
    companion object {
        operator fun invoke(
            appId: String,
            url: String,
            cache: Cache,
            signInSession: Live<Session> = SigningServiceConfig.DEFAULT_SIGN_IN_SESSION,
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            http: HttpClient = KtorClientConfiguration.DEFAULT_HTTP_CLIENT,
            json: Json = KtorClientConfiguration.DEFAULT_JSON,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
        ) = object : BitframeServiceKtorConfig {
            override val signInSession: Live<Session> = signInSession
            override val appId: String = appId
            override val cache: Cache = cache
            override val bus: EventBus = bus
            override val logger: Logger = logger
            override val scope: CoroutineScope = scope
            override val url: String = url
            override val http: HttpClient = http
            override val json: Json = json
        }
    }
}