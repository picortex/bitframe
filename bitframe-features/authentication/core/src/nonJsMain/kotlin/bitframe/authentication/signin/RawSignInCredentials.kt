package bitframe.authentication.signin

actual interface RawSignInCredentials {
    actual val email: String?
    actual val phone: String?
    actual val identifier: String?
    actual val password: String
}