package pimonitor.authentication.signup

import pimonitor.MonitorType
import kotlin.js.JsExport

@JsExport
sealed class SignUpState {

    data class Loading(val message: String) : SignUpState()

    object SelectRegistrationType : SignUpState()

    data class NameEmailForm(
        val params: NameEmailFormParams,
        val prevParams: NameEmailFormParams?
    ) : SignUpState()

    data class Success(val message: String) : SignUpState()

    data class Failure(val cause: Throwable, val message: String? = cause.message) : SignUpState()
}