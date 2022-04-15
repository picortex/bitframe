@file:Suppress("FunctionName")

package pimonitor.client.businesses

import bitframe.client.MiniScope
import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.client.business.interventions.params.CreateInterventionRawFormParams
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.InviteToShareReportsRawFormParams
import pimonitor.core.investments.params.InvestmentRawParams
import presenters.cases.CentralState
import kotlin.js.JsExport
import pimonitor.client.businesses.BusinessesIntent as Intent

internal fun BusinessesScope(config: UIScopeConfig<PiMonitorApi>) = MiniScope {
    viewModel(BusinessesViewModel(config))
    intents(BusinessesIntents(viewModel))
    constants(BusinessesConstants)
}