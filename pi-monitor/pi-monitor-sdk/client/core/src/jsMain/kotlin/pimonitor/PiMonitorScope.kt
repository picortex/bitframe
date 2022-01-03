@file:JsExport

package pimonitor

import bitframe.authentication.signin.exports.SignInScope
import bitframe.panel.PanelScope
import pimonitor.authentication.signup.exports.SignUpScope
import pimonitor.api.PiMonitorService
import pimonitor.evaluation.businesses.exports.BusinessesScope
import pimonitor.evaluation.businesses.exports.CreateBusinessScope
import pimonitor.evaluation.contacts.exports.ContactsScope

open class PiMonitorScope(
    open val signIn: SignInScope,
    open val signUp: SignUpScope,
    open val panel: PanelScope,
    open val businesses: BusinessesScope,
    open val createBusiness: CreateBusinessScope,
    open val contacts: ContactsScope
) {
    @JsName("fromService")
    constructor(service: PiMonitorService) : this(
        SignInScope(service),
        SignUpScope(service),
        PanelScope(service),
        BusinessesScope(service),
        CreateBusinessScope(service),
        ContactsScope(service)
    )
}