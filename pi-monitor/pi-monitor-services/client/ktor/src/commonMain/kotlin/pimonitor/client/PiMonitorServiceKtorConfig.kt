package pimonitor.client

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.signin.Session
import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import bitframe.service.client.config.KtorClientConfiguration
import bitframe.service.client.config.ServiceConfig
import cache.Cache
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import live.Live
import pimonitor.client.evaluation.businesses.BusinessServiceKtorConfig
import pimonitor.client.monitors.MonitorsServiceConfig
import pimonitor.client.monitors.MonitorsServiceKtor
import kotlin.jvm.JvmOverloads

class PiMonitorServiceKtorConfig @JvmOverloads constructor(
    override val appId: String,
    override val url: String,
    override val cache: Cache,
    override val bus: EventBus = InMemoryEventBus(),
    override val http: HttpClient = KtorClientConfiguration.DEFAULT_HTTP_CLIENT,
    override val scope: CoroutineScope = KtorClientConfiguration.DEFAULT_SCOPE
) : KtorClientConfiguration {

    fun with(
        signInService: SignInService
    ): MonitorsServiceConfig = object : MonitorsServiceConfig, ServiceConfig by this {
        override val signInSession: Live<Session> = signInService.session
    }

    fun with(
        monitorsService: MonitorsServiceKtor
    ): BusinessServiceKtorConfig = object : BusinessServiceKtorConfig, KtorClientConfiguration by this {
        override val monitorsService = monitorsService
    }
}