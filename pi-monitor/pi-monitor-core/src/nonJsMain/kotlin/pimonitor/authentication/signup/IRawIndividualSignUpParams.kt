package pimonitor.authentication.signup

actual interface IRawIndividualSignUpParams {
    actual var name: String
    actual var email: String
    actual var password: String
}