package bitframe.authentication.signin

actual interface IRawSignInCredentials {
    actual var email: String
    actual var password: String
}