@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client

import bitframe.client.BitframeAppScopeConfig
import bitframe.client.BitframeReactAppScope
import bitframe.client.SessionAware
import bitframe.client.SessionAwareImpl
import bitframe.client.panel.PanelReactScope
import bitframe.client.password.ChangePasswordReactScope
import bitframe.client.password.ChangePasswordScope
import bitframe.client.signin.SignInReactScope
import pimonitor.client.signup.SignUpReactScope
import pimonitor.client.businesses.BusinessesReactScope
import pimonitor.client.businesses.CreateBusinessReactScope
import pimonitor.client.contacts.ContactsReactScope
import pimonitor.client.portfolio.PortfolioReactScope

class PiMonitorReactAppScope(
    override val config: BitframeAppScopeConfig<PiMonitorApi>,
) : PiMonitorAppScope(config), BitframeReactAppScope<PiMonitorApi> {
    override val signIn by lazy { SignInReactScope(of(api.signIn)) }
    override val signUp by lazy { SignUpReactScope(of(api)) }
    override val panel by lazy { PanelReactScope(of(api.signIn)) }
    override val businesses by lazy { BusinessesReactScope(of(api)) }
    override val createBusiness by lazy { CreateBusinessReactScope(of(api)) }
    override val contacts by lazy { ContactsReactScope(of(api)) }
    override val portfolio by lazy { PortfolioReactScope(of(api)) }
    override val password by lazy { ChangePasswordReactScope(of(api.profile)) }
}