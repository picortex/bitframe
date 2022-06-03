package pimonitor.client.invites

import bitframe.client.ServiceConfigMock
import pimonitor.core.invites.InvitesServiceDaod
import pimonitor.core.invites.InvitesServiceCore

class InvitesServiceMock(
    override val config: ServiceConfigMock
) : InvitesService(config), InvitesServiceCore by InvitesServiceDaod(config)