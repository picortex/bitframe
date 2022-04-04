@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client

import bitframe.client.BitframeAppScope
import bitframe.client.BitframeAppScopeConfig
import bitframe.client.MicroScope
import bitframe.client.SessionAware
import bitframe.client.panel.PanelScope
import bitframe.client.password.ChangePasswordScope
import bitframe.client.signin.SignInScope
import pimonitor.client.business.BusinessDetailsScope
import pimonitor.client.business.financials.BusinessFinancialsScope
import pimonitor.client.business.info.BusinessInfoIntents
import pimonitor.client.business.info.BusinessInfoViewModel
import pimonitor.client.business.interventions.BusinessInterventionsScope
import pimonitor.client.business.investments.BusinessInvestmentsScope
import pimonitor.client.business.operations.BusinessOperationsScope
import pimonitor.client.businesses.BusinessesScope
import pimonitor.client.contacts.ContactsScope
import pimonitor.client.invites.InvitesScope
import pimonitor.client.portfolio.PortfolioScope
import pimonitor.client.search.SearchScope
import pimonitor.client.signup.SignUpScope
import kotlin.js.JsExport

open class PiMonitorAppScope(
    override val config: BitframeAppScopeConfig<PiMonitorApi>,
) : BitframeAppScope<PiMonitorApi> {

    open val api get() = config.api

    override val session: SessionAware get() = config.api.session

    override val signIn by lazy { SignInScope(config { api.signIn }) }
    open val signUp by lazy { SignUpScope(config()) }
    override val panel by lazy { PanelScope(config { api.signIn }) }
    open val businesses by lazy { BusinessesScope(config()) }
    open val contacts by lazy { ContactsScope(config { api.contacts }) }
    open val portfolio by lazy { PortfolioScope(config { api.portfolio }) }
    open val password by lazy { ChangePasswordScope(config { api.profile }) }
    open val search by lazy { SearchScope(config { api.search }) }
    open val integrations by lazy { InvitesScope(config()) }
    open val businessDetails by lazy { BusinessDetailsScope(config { api.businesses }) }
    open val businessFinancials by lazy { BusinessFinancialsScope(config { api.businessFinancials }) }
    open val businessOperations by lazy { BusinessOperationsScope(config { api.businessOperations }) }
    open val businessInvestments by lazy { BusinessInvestmentsScope(config()) }
    open val businessInterventions by lazy { BusinessInterventionsScope(config()) }

    val businessInfo by MicroScope(BusinessInfoViewModel(config())) { intents(BusinessInfoIntents(it)) }
}
