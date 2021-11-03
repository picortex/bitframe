package bitframe.service.client.config

import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface KtorClientConfiguration : ServiceConfig {
    val url: String
    val http: HttpClient

    companion object {
        @JvmStatic
        val DEFAULT_HTTP_CLIENT = HttpClient { }

        @JvmStatic
        val DEFAULT_SCOPE
            get() = ServiceConfig.DEFAULT_SCOPE

        @JvmSynthetic
        operator fun invoke(
            url: String,
            appId: String,
            http: HttpClient = DEFAULT_HTTP_CLIENT,
            scope: CoroutineScope = DEFAULT_SCOPE,
        ): KtorClientConfiguration = object : KtorClientConfiguration {
            override val url: String = url
            override val http: HttpClient = http
            override val appId: String = appId
            override val scope: CoroutineScope = scope
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            url: String,
            appId: String,
            http: HttpClient = DEFAULT_HTTP_CLIENT,
            scope: CoroutineScope = DEFAULT_SCOPE,
        ) = invoke(url, appId, http, scope)
    }
}