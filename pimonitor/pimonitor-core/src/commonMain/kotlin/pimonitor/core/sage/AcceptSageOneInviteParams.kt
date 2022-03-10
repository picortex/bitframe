package pimonitor.core.sage

import kotlinx.serialization.Serializable

@Serializable
class AcceptSageOneInviteParams(
    override val inviteId: String,
    override val companyId: String,
    override val username: String,
    override val password: String,
) : AcceptSageOneInviteRawParams