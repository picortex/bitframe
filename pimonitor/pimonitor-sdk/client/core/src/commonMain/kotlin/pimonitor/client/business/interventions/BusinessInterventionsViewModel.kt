package pimonitor.client.business.interventions

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import pimonitor.client.PiMonitorApi
import pimonitor.client.business.interventions.BusinessInterventionsIntent.*
import pimonitor.core.business.interventions.Intervention
import presenters.cases.CrowdState
import viewmodel.ViewModel

class BusinessInterventionsViewModel(
    val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<BusinessInterventionsIntent, CrowdState<Intervention>>(CrowdState(), config.viewModel) {
    lateinit var businessId: String
    override fun CoroutineScope.execute(i: BusinessInterventionsIntent): Any = when (i) {
        ExitDialog -> TODO()
        is LoadAllInterventions -> TODO()
        is SendCreateDisbursementForm -> TODO()
        is SendCreateGoalForm -> TODO()
        is SendCreateInterventionForm -> TODO()
        is ShowCreateDisbursementForm -> TODO()
        is ShowCreateGoalForm -> TODO()
        is ShowCreateInterventionForm -> TODO()
    }
}