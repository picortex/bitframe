package pimonitor.client

import later.Later
import pimonitor.client.monitors.MonitorsService
import pimonitor.client.monitors.MonitorsServiceConfig
import pimonitor.monitors.Monitor

class MonitorsServiceMock(override val config: MonitorsServiceConfig) : MonitorsService(config) {
    override fun load(uid: String): Later<Monitor?> {
        TODO("Not yet implemented")
    }

    override fun monitor(with: UserRef): Later<Monitor> {
        TODO("Not yet implemented")
    }
}