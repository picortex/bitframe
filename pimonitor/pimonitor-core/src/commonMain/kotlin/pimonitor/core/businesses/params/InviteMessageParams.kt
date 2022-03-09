package pimonitor.core.businesses.params

import kotlinx.serialization.Serializable

@Serializable
data class InviteMessageParams(
    override val businessId: String
) : InviteMessageRawParams