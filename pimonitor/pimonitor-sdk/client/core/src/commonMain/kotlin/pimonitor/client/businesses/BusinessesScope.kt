@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

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

open class BusinessesScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : UIScope<CentralState<MonitoredBusinessSummary>> {

    override val viewModel by lazy { BusinessesViewModel(config) }

    val Dashboard get() = DASHBOARD_OPERATIONAL

    val loadBusinesses: () -> Unit = { viewModel.post(Intent.LoadBusinesses) }

    val showCreateBusinessForm = { params: CreateMonitoredBusinessRawParams? ->
        viewModel.post(Intent.ShowCreateBusinessForm(params))
    }

    val sendCreateBusinessForm = { params: CreateMonitoredBusinessRawParams ->
        viewModel.post(Intent.SendCreateBusinessForm(params))
    }

    val showInviteToShareReport = { monitored: MonitoredBusinessSummary, params: InviteToShareReportsRawFormParams? ->
        viewModel.post(Intent.ShowInviteToShareReportsForm(monitored, params))
    }

    val sendInviteToShareReportsForm = { monitored: MonitoredBusinessSummary, params: InviteToShareReportsRawFormParams ->
        viewModel.post(Intent.SendInviteToShareReportsForm(monitored, params))
    }

    val showInterveneForm = { monitored: MonitoredBusinessSummary, params: CreateInterventionRawFormParams? ->
        viewModel.post(Intent.ShowInterveneForm(monitored, params))
    }

    val sendInterveneForm = { monitored: MonitoredBusinessSummary, params: CreateInterventionRawFormParams ->
        viewModel.post(Intent.SendInterveneForm(monitored, params))
    }

    val showCaptureInvestmentForm = { monitored: MonitoredBusinessSummary, params: InvestmentRawParams? ->
        viewModel.post(Intent.ShowCaptureInvestmentForm(monitored, params))
    }

    val sendCaptureInvestmentForm = { monitored: MonitoredBusinessSummary, params: InvestmentRawParams ->
        viewModel.post(Intent.SendCaptureInvestmentForm(monitored, params))
    }
}