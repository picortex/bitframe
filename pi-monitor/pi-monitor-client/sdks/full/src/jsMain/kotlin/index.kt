@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE")

import bitframe.authentication.signin.SignInScope
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.SignUpScope
import pimonitor.evaluation.business.BusinessScope

fun signIn(service: PiMonitorService) = SignInScope(service.signIn)

fun signUp(service: PiMonitorService) = SignUpScope(service.signUp)

fun business(service: PiMonitorService) = BusinessScope(service.business)