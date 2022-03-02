package pimonitor.core.businesses.params

data class InviteToShareReportsParams(
    override val to: String,
    override val subject: String,
    override val message: String
) : InviteToShareReportsRawParams