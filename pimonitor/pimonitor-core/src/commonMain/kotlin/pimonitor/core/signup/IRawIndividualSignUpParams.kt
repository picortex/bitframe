package pimonitor.core.signup

import kotlin.js.JsExport

@JsExport
interface IRawIndividualSignUpParams {
    val name: String
    val email: String
    val password: String
}