@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")

import bitframe.authentication.signin.exports.SignInScope
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.exports.SignUpScope
import pimonitor.authentication.signup.exports.SignUpScopeLegacy
import pimonitor.evaluation.business.BusinessesScope

fun signIn(service: PiMonitorService) = SignInScope(service.signIn)

fun signUpLegacy(service: PiMonitorService) = SignUpScopeLegacy(service.signUp)

fun signUp(service: PiMonitorService) = SignUpScope(service.signUp)

fun business(service: PiMonitorService) = BusinessesScope(service.businesses)