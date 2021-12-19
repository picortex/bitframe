@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")

import bitframe.api.BitframeService
import bitframe.authentication.signin.exports.SignInReactScope
import bitframe.panel.PanelScope
import pimonitor.authentication.signup.exports.SignUpScope
import pimonitor.api.PiMonitorService
import pimonitor.evaluation.businesses.exports.BusinessesScope
import pimonitor.evaluation.businesses.exports.CreateBusinessReactScope

fun signIn(service: BitframeService) = SignInReactScope(service)

fun signUp(service: PiMonitorService) = SignUpScope(service)

fun panel(service: BitframeService) = PanelScope(service)

fun business(service: PiMonitorService) = BusinessesScope(service)

fun createBusiness(service: PiMonitorService) = CreateBusinessReactScope(service)