package pimonitor.core.sage

class AcceptSageOneInviteParams(
    override val inviteId: String,
    override val companyId: String,
    override val username: String,
    override val password: String,
) : AcceptSageOneInviteRawParams