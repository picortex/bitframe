@file:JsExport
@file:Suppress("PackageDirectoryMismatch")

import pimonitor.MonitorParams
import pimonitor.authentication.signup.SignUpIntent

external interface Params {
    var name: String?
    var email: String?
}

fun stage02Intent(data: Params) = SignUpIntent.Stage02(MonitorParams(data.name, data.email))

fun stage01Intent() = SignUpIntent.Stage01