package pimonitor.core.signup

actual interface IRawBusinessSignUpParams {
    actual var businessName: String
    actual var individualName: String
    actual var individualEmail: String
    actual var password: String
}