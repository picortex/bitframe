package pimonitor.core.signup

data class RawIndividualSignUpParams(
    override var name: String,
    override var email: String,
    override var password: String
) : IRawIndividualSignUpParams