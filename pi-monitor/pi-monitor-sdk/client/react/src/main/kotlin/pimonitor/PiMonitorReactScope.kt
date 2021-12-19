@file:JsExport

package pimonitor

import bitframe.authentication.signin.exports.SignInReactScope
import bitframe.panel.PanelScope
import pimonitor.api.PiMonitorService
import pimonitor.authentication.signup.exports.SignUpReactScope
import pimonitor.evaluation.businesses.exports.BusinessesReactScope
import pimonitor.evaluation.businesses.exports.CreateBusinessReactScope
import pimonitor.evaluation.contacts.exports.ContactsReactScope

class PiMonitorReactScope(
    override val signIn: SignInReactScope,
    override val signUp: SignUpReactScope,
    override val panel: PanelScope,
    override val businesses: BusinessesReactScope,
    override val createBusiness: CreateBusinessReactScope,
    override val contacts: ContactsReactScope
) : PiMonitorScope(signIn, signUp, panel, businesses, createBusiness, contacts) {
    @JsName("fromService")
    constructor(service: PiMonitorService) : this(
        SignInReactScope(service),
        SignUpReactScope(service),
        PanelScope(service),
        BusinessesReactScope(service),
        CreateBusinessReactScope(service),
        ContactsReactScope(service)
    )
}