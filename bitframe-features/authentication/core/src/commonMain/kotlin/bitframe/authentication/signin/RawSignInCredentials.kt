package bitframe.authentication.signin

data class RawSignInCredentials(
    override var email: String,
    override var password: String
) : IRawSignInCredentials