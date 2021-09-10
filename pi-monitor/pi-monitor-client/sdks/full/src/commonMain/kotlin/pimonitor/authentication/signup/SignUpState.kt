package pimonitor.authentication.signup

import pimonitor.Monitor
import pimonitor.MonitorParams
import kotlin.js.JsExport

@JsExport
sealed class SignUpState {
    data class Loading(val message: String) : SignUpState()

    sealed class Form(val level: Int) : SignUpState() {
        data class Stage01(val business: MonitorParams?) : Form(0x01)
        data class Stage02(val business: MonitorParams, val person: MonitorParams?) : Form(0x02)

        val progress = level * 100.0 / 2
    }

    data class Success(val message: String) : SignUpState()

    data class Failure(val cause: Throwable, val message: String? = cause.message) : SignUpState()
}