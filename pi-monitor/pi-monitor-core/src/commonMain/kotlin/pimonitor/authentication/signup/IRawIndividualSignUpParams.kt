package pimonitor.authentication.signup

expect interface IRawIndividualSignUpParams {
    var name: String
    var email: String
    var password: String
}