@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")

import bitframe.authentication.signin.exports.SignInScope
import bitframe.events.EventBus
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.SignUpResult
import pimonitor.authentication.signup.SignUpService
import pimonitor.authentication.signup.exports.SignUpScope
import pimonitor.evaluation.business.BusinessesScope
import react.useEffectOnce

fun signIn(service: PiMonitorService) = SignInScope(service.signIn)

fun signUp(service: PiMonitorService) = SignUpScope(service)

fun business(service: PiMonitorService) = BusinessesScope(service)