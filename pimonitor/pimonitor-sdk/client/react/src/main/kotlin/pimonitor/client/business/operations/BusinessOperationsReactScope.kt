package pimonitor.client.business.operations

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.invites.InfoResults
import presenters.state.State
import viewmodel.asState

class BusinessOperationsReactScope(
    override val config: UIScopeConfig<BusinessesService>
) : BusinessOperationsScope(config), ReactUIScope<State<InfoResults<OperationalDashboard>>> {
    override val useScopeState = { viewModel.asState() }
}