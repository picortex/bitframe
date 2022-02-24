package bitframe.core.profile.params

import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordParams(
    override val previous: String,
    override val current: String
) : ChangePasswordRawParams