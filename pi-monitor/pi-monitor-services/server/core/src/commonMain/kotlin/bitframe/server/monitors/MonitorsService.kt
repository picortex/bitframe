package bitframe.server.monitors

import bitframe.authentication.users.UserRef
import bitframe.daos.conditions.isEqualTo
import later.Later
import later.await
import later.later
import bitframe.monitors.Monitor
import bitframe.monitors.MonitorsService

class MonitorsService(
    override val config: MonitorsServiceConfig
) : MonitorsService(config) {

    private val dao get() = config.monitorsDao
    private val scope get() = config.scope

    override fun load(uid: String): Later<Monitor?> = scope.later {
        dao.all().await().find { it.uid == uid }
    }

    override fun monitor(with: UserRef) = scope.later {
        dao.all("userRef.uid" isEqualTo with.uid).await().first()
    }
}