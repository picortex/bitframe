@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")

import bitframe.BitframeService
import bitframe.authentication.signin.exports.SignInScope
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.exports.SignUpScope
import pimonitor.evaluation.businesses.exports.BusinessesScope
import pimonitor.evaluation.businesses.exports.CreateBusinessScope

fun signIn(service: BitframeService) = SignInScope(service.signIn)

fun signUp(service: PiMonitorService) = SignUpScope(service)

fun business(service: PiMonitorService) = BusinessesScope(service)

fun createBusiness(service: PiMonitorService) = CreateBusinessScope(service)