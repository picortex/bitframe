@file:JsExport

package pimonitor

import bitframe.authentication.signin.exports.SignInScope
import bitframe.panel.PanelScope
import pimonitor.authentication.signup.exports.SignUpScope
import pimonitor.client.PiMonitorService
import pimonitor.evaluation.businesses.exports.BusinessesScope
import pimonitor.evaluation.businesses.exports.CreateBusinessScope

class PiMonitorScope(
    val signIn: SignInScope,
    val signUp: SignUpScope,
    val panel: PanelScope,
    val business: BusinessesScope,
    val createBusiness: CreateBusinessScope
) {
    @JsName("fromService")
    constructor(service: PiMonitorService) : this(
        SignInScope(service),
        SignUpScope(service),
        PanelScope(service),
        BusinessesScope(service),
        CreateBusinessScope(service)
    )
}