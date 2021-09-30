@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")

import bitframe.authentication.signin.SignInScope
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.SignUpScope
import pimonitor.evaluation.business.exports.BusinessesServiceWrapper

fun signIn(service: PiMonitorService) = SignInScope(service.signIn)

fun signUp(service: PiMonitorService) = SignUpScope(service.signUp)

fun business(service: PiMonitorService) = BusinessesServiceWrapper(service.businesses)