package bitframe.authentication.signin

expect interface IRawSignInCredentials {
    var email: String
    var password: String
}