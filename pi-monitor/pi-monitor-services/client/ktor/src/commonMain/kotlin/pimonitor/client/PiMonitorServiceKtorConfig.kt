package pimonitor.client

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.signin.Session
import bitframe.events.EventBus
import bitframe.service.client.config.KtorClientConfiguration
import bitframe.service.client.config.KtorClientConfiguration.Companion.DEFAULT_BUS
import bitframe.service.client.config.KtorClientConfiguration.Companion.DEFAULT_HTTP_CLIENT
import bitframe.service.client.config.KtorClientConfiguration.Companion.DEFAULT_JSON
import bitframe.service.client.config.KtorClientConfiguration.Companion.DEFAULT_SCOPE
import bitframe.service.client.config.ServiceConfig
import cache.Cache
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.Live
import pimonitor.client.evaluation.businesses.BusinessServiceKtorConfig
import pimonitor.client.monitors.MonitorsServiceConfig
import pimonitor.client.monitors.MonitorsServiceKtor
import pimonitor.client.monitors.MonitorsServiceKtorConfig
import kotlin.jvm.JvmOverloads

class PiMonitorServiceKtorConfig @JvmOverloads constructor(
    appId: String,
    url: String,
    cache: Cache,
    json: Json = DEFAULT_JSON,
    bus: EventBus = DEFAULT_BUS,
    http: HttpClient = DEFAULT_HTTP_CLIENT,
    scope: CoroutineScope = DEFAULT_SCOPE
) : KtorClientConfiguration by KtorClientConfiguration(url, appId, cache, bus, http, json, scope) {

    fun with(
        signInService: SignInService
    ): MonitorsServiceKtorConfig = object : MonitorsServiceKtorConfig, KtorClientConfiguration by this {
        override val signInSession: Live<Session> = signInService.session
    }

    fun with(
        monitorsService: MonitorsServiceKtor
    ): BusinessServiceKtorConfig = object : BusinessServiceKtorConfig, KtorClientConfiguration by this {
        override val monitorsService = monitorsService
    }
}