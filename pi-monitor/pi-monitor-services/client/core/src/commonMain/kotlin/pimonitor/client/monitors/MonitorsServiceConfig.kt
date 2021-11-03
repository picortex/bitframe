package pimonitor.client.monitors

import bitframe.authentication.signin.Session
import bitframe.service.client.config.ServiceConfig
import live.Live

interface MonitorsServiceConfig : ServiceConfig {
    val signInSession: Live<Session>
}