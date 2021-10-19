package pimonitor.monitors

import bitframe.authentication.signin.Session
import bitframe.authentication.users.UserRef
import bitframe.service.config.ServiceConfig
import later.Later
import live.Live

class MonitorsServiceKtor(
    override val signInSession: Live<Session>,
    override val config: ServiceConfig
) : MonitorsService(signInSession, config) {
    init {
        watchSignInSession()
    }

    override fun load(uid: String): Later<Monitor?> {
        TODO("Not yet implemented")
    }

    override fun monitor(with: UserRef): Later<Monitor> {
        TODO("Not yet implemented")
    }
}