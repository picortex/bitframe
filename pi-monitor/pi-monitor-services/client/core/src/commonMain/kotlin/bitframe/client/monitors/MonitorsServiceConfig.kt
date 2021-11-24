package bitframe.client.monitors

import bitframe.service.client.config.ServiceConfig
import live.Live
import kotlin.jvm.JvmField
import bitframe.authentication.signin.Session as SignInSession
import bitframe.client.monitors.Session as MonitorSession

interface MonitorsServiceConfig : ServiceConfig {
    val signInSession: Live<SignInSession>
    val monitorSession: Live<MonitorSession>

    companion object {
        @JvmField
        val DEFAULT_MONITOR_SESSION: Live<MonitorSession> = Live(MonitorSession.Unknown)
    }
}