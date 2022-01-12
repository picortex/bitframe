@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor

import bitframe.BitframeReactScope
import bitframe.authentication.signin.exports.SignInReactScope
import bitframe.panel.PanelScope
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
) : PiMonitorScope(signIn, signUp, panel, businesses, createBusiness, contacts), BitframeReactScope {
    @JsName("fromViewModelConfig")
    constructor(config: PiMonitorViewModelConfig) : this(
        SignInReactScope(config),
        SignUpReactScope(config),
        PanelScope(config),
        BusinessesReactScope(config),
        CreateBusinessReactScope(config),
        ContactsReactScope(config)
    )
}