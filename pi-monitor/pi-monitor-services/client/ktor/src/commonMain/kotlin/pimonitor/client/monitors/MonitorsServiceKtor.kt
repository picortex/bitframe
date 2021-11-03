package pimonitor.client.monitors

import bitframe.authentication.users.UserRef
import later.Later
import pimonitor.monitors.Monitor

class MonitorsServiceKtor(
    override val config: MonitorsServiceConfig
) : MonitorsService(config) {
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