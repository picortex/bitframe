package pimonitor.authentication.signup

import pimonitor.Monitor
import pimonitor.MonitorParams
import kotlin.js.JsExport

@JsExport
sealed class SignUpIntent {
    object Stage01 : SignUpIntent()
    data class Stage02(val business: MonitorParams) : SignUpIntent()
    data class Submit(val person: Monitor.Person) : SignUpIntent()
}