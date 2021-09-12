@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "PackageDirectoryMismatch")

import bitframe.authentication.signIn.SignInScope
import pimonitor.PiMonitorService

fun signIn(service: PiMonitorService) = SignInScope(service.signIn)
