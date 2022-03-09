@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.InviteToShareReportsRawFormParams
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

open class BusinessesScope(
    override val config: UIScopeConfig<BusinessesService>
) : UIScope<Intent, State> {

    override val viewModel: ViewModel<Intent, State> by lazy { BusinessesViewModel(config) }

    val Dashboard get() = DASHBOARD_OPERATIONAL

    val loadBusinesses: () -> Unit = { viewModel.post(Intent.LoadBusinesses) }

    val submitCreateBusinessForm = { params: CreateMonitoredBusinessRawParams ->
        post(Intent.SendCreateBusinessForm(params))
    }

    val submitInviteToShareReportsForm = { params: InviteToShareReportsRawFormParams ->
        post(Intent.SendInviteToShareReportsForm(params))
    }

    val exitDialog: () -> Unit = { viewModel.post(Intent.ExitDialog) }

    val showCreateBusinessForm: () -> Unit = { viewModel.post(Intent.ShowCreateBusinessForm) }
}