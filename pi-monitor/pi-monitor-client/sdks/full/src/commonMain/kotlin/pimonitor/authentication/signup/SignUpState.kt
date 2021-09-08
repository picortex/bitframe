package pimonitor.authentication.signup

import pimonitor.Monitor
import kotlin.js.JsExport

@JsExport
sealed class SignUpState {
    data class Loading(val message: String) : SignUpState()

    sealed class Form(val level: Int) : SignUpState() {
        data class Stage01(val business: Monitor.Business?) : Form(0x01)
        data class Stage02(val business: Monitor.Business) : Form(0x02)

        val progress = level * 100.0 / 2
    }

    data class Error(val cause: Throwable) : SignUpState()
}