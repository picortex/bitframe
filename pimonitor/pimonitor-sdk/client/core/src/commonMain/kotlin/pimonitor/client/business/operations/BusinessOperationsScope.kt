@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.operations

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService
import pimonitor.core.business.params.LoadReportRawParams
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.dashboards.OperationalDifferenceBoard
import pimonitor.core.invites.InfoResults
import presenters.cases.State
import kotlin.js.JsExport

open class BusinessOperationsScope(
    override val config: UIScopeConfig<BusinessOperationsService>,
) : UIScope<State<InfoResults<OperationalDifferenceBoard>>> {
    override val viewModel by lazy { BusinessOperationsViewModel(config) }

    val loadOperationalDashboard = { params: LoadReportRawParams ->
        viewModel.post(BusinessOperationsIntent.LoadOperationalDashboard(params))
    }
}