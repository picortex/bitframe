package pimonitor.core.picortex

import kotlinx.serialization.Serializable

@Serializable
class AcceptPicortexInviteParams(
    override val inviteId: String,
    override val subdomain: String,
    override val secret: String
) : AcceptPicortexInviteRawParams