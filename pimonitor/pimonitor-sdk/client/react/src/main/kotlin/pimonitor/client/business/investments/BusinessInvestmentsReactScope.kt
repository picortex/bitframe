package pimonitor.client.business.investments

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.core.business.investments.Investment
import presenters.cases.CrowdState
import viewmodel.asState

class BusinessInvestmentsReactScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : BusinessInvestmentsScope(config), ReactUIScope<CrowdState<Investment>> {
    override val useScopeState = { viewModel.asState() }
}