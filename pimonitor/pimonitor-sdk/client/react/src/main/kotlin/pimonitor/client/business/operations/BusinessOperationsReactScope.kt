@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.operations

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.dashboards.OperationalDifferenceBoard
import pimonitor.core.invites.InfoResults
import presenters.cases.State
import viewmodel.asState

class BusinessOperationsReactScope(
    override val config: UIScopeConfig<BusinessesService>
) : BusinessOperationsScope(config), ReactUIScope<State<InfoResults<OperationalDifferenceBoard>>> {
    override val useScopeState = { viewModel.asState() }
}