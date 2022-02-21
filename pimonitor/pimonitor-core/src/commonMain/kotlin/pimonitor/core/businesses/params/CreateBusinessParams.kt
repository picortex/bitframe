package pimonitor.core.businesses.params

import kotlinx.serialization.Serializable

@Serializable
class CreateBusinessParams(
    override val businessName: String,
    override val contactName: String,
    override val contactEmail: String,
    override val sendInvite: Boolean = true
) : RawCreateBusinessParams