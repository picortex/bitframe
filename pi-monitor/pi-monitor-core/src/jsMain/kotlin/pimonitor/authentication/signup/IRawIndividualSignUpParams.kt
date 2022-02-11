@file:JsExport

package pimonitor.authentication.signup

actual external interface IRawIndividualSignUpParams {
    actual var name: String
    actual var email: String
    actual var password: String
}