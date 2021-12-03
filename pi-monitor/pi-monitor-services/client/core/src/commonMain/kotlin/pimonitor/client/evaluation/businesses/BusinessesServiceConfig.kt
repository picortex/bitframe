package pimonitor.client.evaluation.businesses

import bitframe.service.client.config.ServiceConfig
import live.Live
import pimonitor.client.monitors.Session

interface BusinessesServiceConfig : ServiceConfig {
    val monitorSession: Live<Session>
}