package pimonitor.client.invites

import bitframe.client.ServiceConfigMock
import pimonitor.core.invites.InvitesDaodService
import pimonitor.core.invites.InvitesServiceCore

class InvitesServiceMock(
    override val config: ServiceConfigMock
) : InvitesService(config), InvitesServiceCore by InvitesDaodService(config)