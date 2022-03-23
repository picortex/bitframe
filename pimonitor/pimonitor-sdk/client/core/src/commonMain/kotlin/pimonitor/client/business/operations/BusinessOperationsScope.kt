@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.operations

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.invites.InfoResults
import presenters.cases.State
import kotlin.js.JsExport

open class BusinessOperationsScope(
    override val config: UIScopeConfig<BusinessesService>,
) : UIScope<State<InfoResults<OperationalDashboard>>> {
    override val viewModel by lazy { BusinessOperationsViewModel(config) }

    val loadOperationalDashboard = { businessId: String ->
        viewModel.post(BusinessOperationsIntent.LoadOperationalDashboard(businessId))
    }
}