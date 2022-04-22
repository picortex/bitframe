@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client

import bitframe.client.BitframeAppScopeConfig
import bitframe.client.BitframeReactAppScope
import bitframe.client.panel.PanelReactScope
import bitframe.client.password.ChangePasswordReactScope
import bitframe.client.signin.SignInReactScope
import pimonitor.client.contacts.ContactsReactScope
import pimonitor.client.invites.InvitesReactScope
import pimonitor.client.portfolio.PortfolioReactScope
import pimonitor.client.search.SearchReactScope
import pimonitor.client.signup.SignUpReactScope

class PiMonitorReactAppScope(
    private val config: BitframeAppScopeConfig<PiMonitorApi>,
) : PiMonitorAppScope(config), BitframeReactAppScope<PiMonitorApi> {
    override val signIn by lazy { SignInReactScope(config { api.signIn }) }
    override val signUp by lazy { SignUpReactScope(config()) }
    override val panel by lazy { PanelReactScope(config { api.signIn }) }
    override val contacts by lazy { ContactsReactScope(config { api.contacts }) }
    override val portfolio by lazy { PortfolioReactScope(config { api.portfolio }) }
    override val password by lazy { ChangePasswordReactScope(config { api.profile }) }
    override val search by lazy { SearchReactScope(config { api.search }) }
    override val integrations by lazy { InvitesReactScope(config()) }
}