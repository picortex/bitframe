package pimonitor.client.monitors

import bitframe.service.client.config.ServiceConfig
import live.MutableLive
import kotlin.jvm.JvmField
import bitframe.authentication.signin.Session as SignInSession
import pimonitor.client.monitors.Session as MonitorSession

interface MonitorsServiceConfig : ServiceConfig {
    val signInSession: MutableLive<SignInSession>
    val monitorSession: MutableLive<MonitorSession>

    companion object {
        @JvmField
        val DEFAULT_MONITOR_SESSION: MutableLive<MonitorSession> = MutableLive(MonitorSession.Unknown)
    }
}