package pimonitor.core.businesses.params

class CreateBusinessParams(
    override val businessName: String,
    override val contactName: String,
    override val contactEmail: String,
    override val sendInvite: Boolean = true
) : RawCreateBusinessParams