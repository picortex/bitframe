@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import pimonitor.client.interventions.params.CreateInterventionRawFormParams
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.InviteToShareReportsRawFormParams
import pimonitor.core.interventions.params.InterventionRawParams
import pimonitor.core.investments.params.InvestmentRawParams
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.client.businesses.BusinessesIntent as Intent

open class BusinessesIntents internal constructor(
    private val viewModel: ViewModel<Intent, *>
) {
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
}