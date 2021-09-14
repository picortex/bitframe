@file:JsExport
@file:Suppress("PackageDirectoryMismatch")

import pimonitor.PiMonitorService
import pimonitor.authentication.signup.SignUpScope

fun signUp(service: PiMonitorService) = SignUpScope(service)