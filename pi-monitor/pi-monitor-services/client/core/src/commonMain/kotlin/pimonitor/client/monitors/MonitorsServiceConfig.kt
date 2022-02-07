package pimonitor.client.monitors

import bitframe.service.client.config.ServiceConfig
import live.MutableLive
import kotlin.jvm.JvmField
import pimonitor.client.monitors.Session as MonitorSession

interface MonitorsServiceConfig : ServiceConfig {
    val monitorSession: MutableLive<MonitorSession>

    companion object {
        @JvmField
        val DEFAULT_MONITOR_SESSION: MutableLive<MonitorSession> = MutableLive(MonitorSession.Unknown)
    }
}