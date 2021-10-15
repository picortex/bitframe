package pimonitor.monitors

import bitframe.authentication.signin.Session
import bitframe.authentication.users.UserRef
import bitframe.daos.conditions.isEqualTo
import bitframe.service.config.ServiceConfig
import later.await
import later.later
import live.Live

class MonitorsServiceImpl(
    override val signInSession: Live<Session>,
    private val dao: MonitorDao,
    override val config: ServiceConfig
) : MonitorsService(signInSession, config) {
    init {
        watchSignInSession()
    }

    override fun monitor(with: UserRef) = scope.later {
        dao.all("userRef.uid" isEqualTo with.uid).await().first()
    }
}