package pimonitor.core.signup

expect interface IRawBusinessSignUpParams {
    var businessName: String
    var individualName: String
    var individualEmail: String
    var password: String
}