package pimonitor.client.business.interventions

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.core.business.interventions.Intervention
import presenters.cases.CrowdState

open class BusinessInterventionsScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : UIScope<CrowdState<Intervention>> {
    override val viewModel by lazy { BusinessInterventionsViewModel(config) }
}