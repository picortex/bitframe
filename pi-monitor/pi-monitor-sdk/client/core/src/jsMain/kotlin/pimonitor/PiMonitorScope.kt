@file:JsExport

package pimonitor

import bitframe.BitframeScope
import bitframe.authentication.signin.exports.SignInScope
import bitframe.panel.PanelScope
import pimonitor.authentication.signup.exports.SignUpScope
import pimonitor.evaluation.businesses.exports.BusinessesScope
import pimonitor.evaluation.businesses.exports.CreateBusinessScope
import pimonitor.evaluation.contacts.exports.ContactsScope

open class PiMonitorScope(
    override val signIn: SignInScope,
    open val signUp: SignUpScope,
    override val panel: PanelScope,
    open val businesses: BusinessesScope,
    open val createBusiness: CreateBusinessScope,
    open val contacts: ContactsScope
) : BitframeScope {
    @JsName("fromViewModelConfig")
    constructor(config: PiMonitorViewModelConfig) : this(
        SignInScope(config),
        SignUpScope(config),
        PanelScope(config),
        BusinessesScope(config),
        CreateBusinessScope(config),
        ContactsScope(config)
    )
}