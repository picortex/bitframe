@file:JsExport

package pimonitor.core.signup

actual external interface IRawBusinessSignUpParams {
    actual var businessName: String
    actual var individualName: String
    actual var individualEmail: String
    actual var password: String
}