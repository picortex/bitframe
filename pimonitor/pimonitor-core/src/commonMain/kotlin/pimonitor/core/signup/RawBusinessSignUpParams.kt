package pimonitor.core.signup

data class RawBusinessSignUpParams(
    override var businessName: String,
    override var individualName: String,
    override var individualEmail: String,
    override var password: String
) : IRawBusinessSignUpParams