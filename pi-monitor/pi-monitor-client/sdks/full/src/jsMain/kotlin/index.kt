@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")

import bitframe.client.BitframeService
import bitframe.authentication.signin.exports.SignInScope
import bitframe.panel.PanelScope
import bitframe.authentication.signup.exports.SignUpScope
import bitframe.client.PiMonitorService
import bitframe.evaluation.businesses.exports.BusinessesScope
import bitframe.evaluation.businesses.exports.CreateBusinessScope

fun signIn(service: BitframeService) = SignInScope(service)

fun signUp(service: PiMonitorService) = SignUpScope(service)

fun panel(service: BitframeService) = PanelScope(service)

fun business(service: PiMonitorService) = BusinessesScope(service)

fun createBusiness(service: PiMonitorService) = CreateBusinessScope(service)