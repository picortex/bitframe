@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client

import bitframe.client.BitframeAppScope
import bitframe.client.BitframeAppScopeConfig
import bitframe.client.SessionAware
import bitframe.client.panel.PanelScope
import bitframe.client.password.ChangePasswordScope
import bitframe.client.signin.SignInScope
import pimonitor.client.business.financials.BusinessFinancialsScope
import pimonitor.client.business.index.BusinessIndexScope
import pimonitor.client.business.info.BusinessInfoScope
import pimonitor.client.business.operations.BusinessOperationsScope
import pimonitor.client.business.overview.BusinessOverviewScope
import pimonitor.client.businesses.BusinessesScope
import pimonitor.client.contacts.ContactsScope
import pimonitor.client.interventions.InterventionsScope
import pimonitor.client.investment.index.InvestmentIndexScope
import pimonitor.client.investments.InvestmentScope
import pimonitor.client.invites.InvitesScope
import pimonitor.client.portfolio.PortfolioScope
import pimonitor.client.search.SearchScope
import pimonitor.client.signup.SignUpScope
import kotlin.js.JsExport

open class PiMonitorAppScope(
    private val config: BitframeAppScopeConfig<PiMonitorApi>,
) : BitframeAppScope<PiMonitorApi> {

    override val api: PiMonitorApi get() = config.api

    override val session: SessionAware get() = config.api.session

    override val signIn by lazy { SignInScope(config { api.signIn }) }
    open val signUp by lazy { SignUpScope(config()) }
    override val panel by lazy { PanelScope(config { api.signIn }) }
    val businesses by BusinessesScope(config())
    open val contacts by lazy { ContactsScope(config { api.contacts }) }
    open val portfolio by lazy { PortfolioScope(config { api.portfolio }) }
    open val password by lazy { ChangePasswordScope(config { api.profile }) }
    open val search by lazy { SearchScope(config { api.search }) }
    val interventions by InterventionsScope(config())
    val investments by InvestmentScope(config())
    val investmentIndex by InvestmentIndexScope(config { api.investments })
    open val integrations by lazy { InvitesScope(config()) }
    val businessIndex by BusinessIndexScope(config { api.businesses })
    val businessOverview by BusinessOverviewScope(config())
    open val businessFinancials by lazy { BusinessFinancialsScope(config { api.businessFinancials }) }
    open val businessOperations by lazy { BusinessOperationsScope(config { api.businessOperations }) }
    val businessInfo by BusinessInfoScope(config())
}
