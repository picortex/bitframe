package pimonitor.core.businesses.params

import kotlinx.serialization.Serializable
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import kotlin.jvm.JvmField

@Serializable
data class InviteToShareReportsParams(
    override val business: MonitoredBusinessSummary,
    override val to: String,
    override val subject: String,
    override val message: String
) : InviteToShareReportsRawParams {
    companion object {
        @JvmField
        val DEFAULT_INVITE_SUBJECT = "Invite to your share reports"

        @JvmField
        val DEFAULT_INVITE_MESSAGE = "We would like to invite you to share your financial and technical reports with us"
    }
}