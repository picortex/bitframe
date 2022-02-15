@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client

import bitframe.client.BitframeReactAppScope
import bitframe.client.panel.PanelReactScope
import bitframe.client.signin.SignInReactScope
import pimonitor.client.signup.SignUpReactScope
import pimonitor.client.businesses.BusinessesReactScope
import pimonitor.client.businesses.CreateBusinessReactScope
import pimonitor.client.contacts.ContactsReactScope
import pimonitor.client.portfolio.PortfolioReactScope

@JsExport
class PiMonitorReactAppScope(
    override val config: PiMonitorAppScopeConfig,
) : PiMonitorAppScope(config), BitframeReactAppScope {
    override val signIn by lazy { SignInReactScope(of(api.signIn)) }
    override val signUp by lazy { SignUpReactScope(of(api.register)) }
    override val panel by lazy { PanelReactScope(of(api.signIn)) }
    override val businesses by lazy { BusinessesReactScope(of(api)) }
    override val createBusiness by lazy { CreateBusinessReactScope(of(api)) }
    override val contacts by lazy { ContactsReactScope(of(api)) }
    override val portfolio by lazy { PortfolioReactScope(of(api)) }
}