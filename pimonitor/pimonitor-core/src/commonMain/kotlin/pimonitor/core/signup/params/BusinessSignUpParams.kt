package pimonitor.core.signup.params

import bitframe.core.signin.SignInCredentials
import bitframe.core.users.RegisterUserParams
import kotlinx.serialization.Serializable
import pimonitor.core.spaces.SPACE_SCOPE
import pimonitor.core.spaces.SPACE_TYPE
import pimonitor.core.users.USER_TYPE
import validation.requiredNotBlank

@Serializable
data class BusinessSignUpParams(
    override val businessName: String,
    override val individualName: String,
    override val individualEmail: String,
    override val password: String
) : BusinessSignUpRawParams {
    override fun toSignInCredentials() = SignInCredentials(
        identifier = requiredNotBlank(::individualEmail),
        password = requiredNotBlank(::password)
    )

    override fun toRegisterUserParams() = RegisterUserParams(
        userName = requiredNotBlank(::individualName),
        userIdentifier = requiredNotBlank(::individualEmail),
        userPassword = requiredNotBlank(::password),
        userType = USER_TYPE.MONITOR,
        spaceName = requiredNotBlank(::businessName),
        spaceType = SPACE_TYPE.COOPERATE_MONITOR,
        spaceScope = SPACE_SCOPE
    )
}