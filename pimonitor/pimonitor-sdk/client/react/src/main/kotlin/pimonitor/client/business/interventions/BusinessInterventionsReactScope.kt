@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.interventions

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.core.business.interventions.Intervention
import presenters.cases.CrowdState
import viewmodel.asState

class BusinessInterventionsReactScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : BusinessInterventionsScope(config), ReactUIScope<CrowdState<Intervention>> {
    override val useScopeState = { viewModel.asState() }
}