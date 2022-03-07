package pimonitor.core.picortex

class AcceptPicortexInviteParams(
    override val inviteId: String,
    override val subdomain: String,
    override val secret: String
) : AcceptPicortexInviteRawParams