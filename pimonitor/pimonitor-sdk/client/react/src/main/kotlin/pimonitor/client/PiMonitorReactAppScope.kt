@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client

import bitframe.client.BitframeAppScopeConfig
import bitframe.client.BitframeReactAppScope
import bitframe.client.panel.PanelReactScope
import bitframe.client.password.ChangePasswordReactScope
import bitframe.client.signin.SignInReactScope
import pimonitor.client.business.BusinessDetailsReactScope
import pimonitor.client.business.financials.BusinessFinancialsReactScope
import pimonitor.client.business.financials.BusinessFinancialsScope
import pimonitor.client.business.operations.BusinessOperationsReactScope
import pimonitor.client.businesses.BusinessesReactScope
import pimonitor.client.contacts.ContactsReactScope
import pimonitor.client.invites.InvitesReactScope
import pimonitor.client.portfolio.PortfolioReactScope
import pimonitor.client.search.SearchReactScope
import pimonitor.client.signup.SignUpReactScope

class PiMonitorReactAppScope(
    override val config: BitframeAppScopeConfig<PiMonitorApi>,
) : PiMonitorAppScope(config), BitframeReactAppScope<PiMonitorApi> {
    override val signIn by lazy { SignInReactScope(config { api.signIn }) }
    override val signUp by lazy { SignUpReactScope(config()) }
    override val panel by lazy { PanelReactScope(config { api.signIn }) }
    override val businesses by lazy { BusinessesReactScope(config()) }
    override val contacts by lazy { ContactsReactScope(config { api.contacts }) }
    override val portfolio by lazy { PortfolioReactScope(config { api.portfolio }) }
    override val password by lazy { ChangePasswordReactScope(of(api.profile)) }
    override val search by lazy { SearchReactScope(config { api.search }) }
    override val integrations by lazy { InvitesReactScope(config()) }
    override val businessDetails by lazy { BusinessDetailsReactScope(config { api.businesses }) }
    override val businessOperations by lazy { BusinessOperationsReactScope(config { api.businesses }) }
    override val businessFinancials by lazy { BusinessFinancialsReactScope(config { api.businesses }) }
}