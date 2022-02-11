@file:JsExport

package pimonitor.authentication.signup

actual external interface IRawBusinessSignUpParams {
    actual var businessName: String
    actual var individualName: String
    actual var individualEmail: String
    actual var password: String
}