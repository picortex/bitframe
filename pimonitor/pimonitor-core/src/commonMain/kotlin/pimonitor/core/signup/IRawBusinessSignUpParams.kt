package pimonitor.core.signup

import kotlin.js.JsExport

@JsExport
interface IRawBusinessSignUpParams {
    val businessName: String
    val individualName: String
    val individualEmail: String
    val password: String
}