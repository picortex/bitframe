package pimonitor.client

import bitframe.actors.users.UserRef
import later.Later

class MonitorsServiceMock(override val config: MonitorsServiceConfig) : MonitorsService(config) {
    override fun load(uid: String): Later<Monitor?> {
        TODO("Not yet implemented")
    }

    override fun monitor(with: UserRef): Later<Monitor> {
        TODO("Not yet implemented")
    }
}