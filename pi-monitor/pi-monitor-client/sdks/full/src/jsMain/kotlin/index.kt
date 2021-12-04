@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")

import bitframe.client.BitframeService
import bitframe.authentication.signin.exports.SignInScope
import bitframe.panel.PanelScope
import pimonitor.PiMonitorScope
import pimonitor.authentication.signup.exports.SignUpScope
import pimonitor.client.PiMonitorService
import pimonitor.evaluation.businesses.exports.BusinessesScope
import pimonitor.evaluation.businesses.exports.CreateBusinessScope

fun signIn(service: BitframeService) = SignInScope(service)

fun signUp(service: PiMonitorService) = SignUpScope(service)

fun panel(service: BitframeService) = PanelScope(service)

fun business(service: PiMonitorService) = BusinessesScope(service)

fun createBusiness(service: PiMonitorService) = CreateBusinessScope(service)

fun scope(config: ServiceConfiguration) = PiMonitorScope(client(config))