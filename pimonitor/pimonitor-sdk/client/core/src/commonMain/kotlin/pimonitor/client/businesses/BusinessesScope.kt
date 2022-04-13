@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.client.business.investments.params.InvestmentsRawParams
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.InviteToShareReportsRawFormParams
import kotlin.js.JsExport
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

open class BusinessesScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : UIScope<State> {

    override val viewModel by lazy { BusinessesViewModel(config) }

    val Dashboard get() = DASHBOARD_OPERATIONAL

    val loadBusinesses: () -> Unit = { viewModel.post(Intent.LoadBusinesses) }

    val showCreateBusinessForm: () -> Unit = { viewModel.post(Intent.ShowCreateBusinessForm) }

    val sendCreateBusinessForm = { params: CreateMonitoredBusinessRawParams ->
        viewModel.post(Intent.SendCreateBusinessForm(params))
    }

    val showInviteToShareReport = { params: MonitoredBusinessSummary ->
        viewModel.post(Intent.ShowInviteToShareReportsForm(params))
    }

    val sendInviteToShareReportsForm = { params: InviteToShareReportsRawFormParams ->
        viewModel.post(Intent.SendInviteToShareReportsForm(params))
    }

    val showInterveneForm = { params: MonitoredBusinessSummary ->
        viewModel.post(Intent.ShowInterveneForm(params))
    }

    val sendInterveneForm = { params: Any ->
        TODO()
    }

    val showCaptureInvestmentForm = { params: MonitoredBusinessSummary ->
        viewModel.post(Intent.ShowCaptureInvestmentForm(params))
    }

    val sendCaptureInvestmentForm = { params: InvestmentsRawParams ->
        viewModel.post(Intent.SendCaptureInvestmentForm(params))
    }

    val exitDialog: () -> Unit = { viewModel.post(Intent.ExitDialog) }
}