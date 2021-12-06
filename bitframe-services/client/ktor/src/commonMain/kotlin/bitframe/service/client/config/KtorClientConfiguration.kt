package bitframe.service.client.config

import events.EventBus
import cache.Cache
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface KtorClientConfiguration : ServiceConfig {
    val url: String
    val http: HttpClient
    val json: Json

    companion object {
        @JvmField
        val DEFAULT_HTTP_CLIENT = HttpClient { }

        @JvmField
        val DEFAULT_SCOPE = ServiceConfig.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_BUS = ServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_JSON = Json.Default

        @JvmSynthetic
        operator fun invoke(
            url: String,
            appId: String,
            cache: Cache,
            bus: EventBus = DEFAULT_BUS,
            http: HttpClient = DEFAULT_HTTP_CLIENT,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE,
        ): KtorClientConfiguration = object : KtorClientConfiguration, ServiceConfig by ServiceConfig(appId, cache, bus, scope) {
            override val url: String = url
            override val http: HttpClient = http
            override val json: Json = json
            override val appId: String = appId
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            url: String,
            appId: String,
            cache: Cache,
            bus: EventBus = DEFAULT_BUS,
            http: HttpClient = DEFAULT_HTTP_CLIENT,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE,
        ) = invoke(url, appId, cache, bus, http, json, scope)
    }
}