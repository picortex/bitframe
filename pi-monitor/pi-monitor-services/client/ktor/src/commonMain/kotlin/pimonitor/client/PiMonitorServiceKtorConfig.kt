package pimonitor.client

import bitframe.authentication.client.signin.SignInServiceConfig.Companion.DEFAULT_SIGN_IN_SESSION
import bitframe.authentication.client.signin.SignInServiceKtorConfig
import bitframe.client.BitframeServiceConfig
import bitframe.events.EventBus
import bitframe.service.client.config.KtorClientConfiguration.Companion.DEFAULT_BUS
import bitframe.service.client.config.KtorClientConfiguration.Companion.DEFAULT_HTTP_CLIENT
import bitframe.service.client.config.KtorClientConfiguration.Companion.DEFAULT_JSON
import bitframe.service.client.config.KtorClientConfiguration.Companion.DEFAULT_SCOPE
import cache.Cache
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.Live
import pimonitor.client.evaluation.businesses.BusinessServiceKtorConfig
import pimonitor.client.monitors.MonitorsServiceConfig.Companion.DEFAULT_MONITOR_SESSION
import pimonitor.client.monitors.MonitorsServiceKtorConfig
import kotlin.jvm.JvmOverloads
import bitframe.authentication.signin.Session as SignInSession
import pimonitor.client.monitors.Session as MonitorSession

class PiMonitorServiceKtorConfig @JvmOverloads constructor(
    override val appId: String,
    override val url: String,
    override val cache: Cache,
    override val signInSession: Live<SignInSession> = DEFAULT_SIGN_IN_SESSION,
    override val monitorSession: Live<MonitorSession> = DEFAULT_MONITOR_SESSION,
    override val json: Json = DEFAULT_JSON,
    override val bus: EventBus = DEFAULT_BUS,
    override val http: HttpClient = DEFAULT_HTTP_CLIENT,
    override val scope: CoroutineScope = DEFAULT_SCOPE,
) : BitframeServiceConfig,
    SignInServiceKtorConfig,
    MonitorsServiceKtorConfig,
    BusinessServiceKtorConfig