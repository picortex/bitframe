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
import pimonitor.client.business.info.BusinessInfoScope
import pimonitor.client.business.operations.BusinessOperationsScope
import pimonitor.client.business.overview.BusinessOverviewScope
import pimonitor.client.businesses.BusinessesScope
import pimonitor.client.contacts.ContactsScope
import pimonitor.client.intervention.disbursements.InterventionDisbursementScope
import pimonitor.client.interventions.InterventionsScope
import pimonitor.client.investment.disbursements.InvestmentDisbursementScope
import pimonitor.client.investments.InvestmentScope
import pimonitor.client.invites.InvitesScope
import pimonitor.client.portfolio.PortfolioScope
import pimonitor.client.search.SearchScope
import pimonitor.client.signup.SignUpScope
import kotlin.js.JsExport

open class PiMonitorAppScope(
    private val config: BitframeAppScopeConfig<MonitorApi>,
) : BitframeAppScope<MonitorApi> {

    override val api: MonitorApi get() = config.api

    override val session: SessionAware get() = config.api.session

    override val signIn by SignInScope(config { api.signIn })

    override val panel by PanelScope(config { api.signIn })
    open val signUp by lazy { SignUpScope(config()) }
    val businesses by BusinessesScope(config())
    val businessInfo by BusinessInfoScope(config())
    val businessOverview by BusinessOverviewScope(config())
    val businessFinancials by BusinessFinancialsScope(config { api.businessFinancials })
    val businessOperations by BusinessOperationsScope(config { api.businessOperations })
    open val contacts by lazy { ContactsScope(config { api.contacts }) }
    val portfolio by PortfolioScope(config { api.portfolio })
    open val password by lazy { ChangePasswordScope(config { api.profile }) }
    open val search by lazy { SearchScope(config { api.search }) }
    val interventions by InterventionsScope(config())
    val interventionDisbursement by InterventionDisbursementScope(config { api.interventions })
    val investments by InvestmentScope(config())
    val investmentDisbursements by InvestmentDisbursementScope(config { api.investments })
    open val integrations by lazy { InvitesScope(config()) }
}
