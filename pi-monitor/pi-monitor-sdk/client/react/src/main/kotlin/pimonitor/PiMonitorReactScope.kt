@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor

import bitframe.BitframeReactScope
import bitframe.authentication.signin.exports.SignInReactScope
import bitframe.panel.PanelReactScope
import pimonitor.authentication.signup.exports.SignUpReactScope
import pimonitor.evaluation.businesses.exports.BusinessesReactScope
import pimonitor.evaluation.businesses.exports.CreateBusinessReactScope
import pimonitor.evaluation.contacts.exports.ContactsReactScope
import pimonitor.portfolio.PortfolioReactScope

class PiMonitorReactScope private constructor(
    override val config: PiMonitorScopeConfig,
    override val signIn: SignInReactScope,
    override val signUp: SignUpReactScope,
    override val panel: PanelReactScope,
    override val businesses: BusinessesReactScope,
    override val createBusiness: CreateBusinessReactScope,
    override val contacts: ContactsReactScope,
    override val portfolio: PortfolioReactScope
) : PiMonitorScope(config, signIn, signUp, panel, businesses, createBusiness, contacts, portfolio),
    BitframeReactScope {
    @JsName("_init_fromViewModelConfig")
    constructor(config: PiMonitorScopeConfig) : this(
        config,
        SignInReactScope(config),
        SignUpReactScope(config),
        PanelReactScope(config),
        BusinessesReactScope(config),
        CreateBusinessReactScope(config),
        ContactsReactScope(config),
        PortfolioReactScope(config)
    )
}