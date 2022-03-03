package pimonitor.core.businesses.params

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmField

@Serializable
data class InviteToShareReportsParams(
    override val to: String,
    override val subject: String,
    override val message: String
) : InviteToShareReportsRawParams {
    companion object {
        @JvmField
        val DEFAULT_INVITE_SUBJECT = "Invite to share reports"

        @JvmField
        val DEFAULT_INVITE_MESSAGE = "We would like to invite you to share your financial and technical reports with us"
    }
}