package bitframe.client.evaluation.businesses

import bitframe.service.client.config.ServiceConfig
import live.Live
import bitframe.client.monitors.Session

interface BusinessesServiceConfig : ServiceConfig {
    val monitorSession: Live<Session>
}