package pimonitor.authentication.signup

import kotlin.js.JsExport

@JsExport
sealed class SignUpIntent {
    object Stage01 : SignUpIntent()
    data class Stage02(val business: Data) : SignUpIntent() {
        data class Data(val name: String?, val email: String?)
    }
}