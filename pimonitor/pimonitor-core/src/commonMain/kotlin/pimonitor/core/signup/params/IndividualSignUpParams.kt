package pimonitor.core.signup.params

import bitframe.core.signin.SignInCredentials
import bitframe.core.users.RegisterUserParams
import identifier.Name
import kotlinx.serialization.Serializable
import pimonitor.core.spaces.SPACE_SCOPE
import pimonitor.core.spaces.SPACE_TYPE
import pimonitor.core.users.USER_TYPE
import validation.requiredNotBlank

@Serializable
data class IndividualSignUpParams(
    override val name: String,
    override val email: String,
    override val password: String
) : SignUpParams(), IndividualSignUpRawParams {
    override fun toSignInCredentials() = SignInCredentials(
        identifier = requiredNotBlank(::email),
        password = requiredNotBlank(::password)
    )

    override fun toRegisterUserParams() = RegisterUserParams(
        userName = requiredNotBlank(::name),
        userIdentifier = requiredNotBlank(::email),
        userPassword = requiredNotBlank(::password),
        userType = USER_TYPE.MONITOR,
        spaceName = "${Name(name).first}'s Space",
        spaceType = SPACE_TYPE.INDIVIDUAL_MONITOR,
        spaceScope = SPACE_SCOPE
    )
}