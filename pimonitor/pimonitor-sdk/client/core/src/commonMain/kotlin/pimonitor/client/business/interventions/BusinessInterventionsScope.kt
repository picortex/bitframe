package pimonitor.client.business.interventions

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.client.business.interventions.BusinessInterventionsIntent.*
import pimonitor.client.business.interventions.params.CreateGoalRawFormParams
import pimonitor.client.business.interventions.params.CreateInterventionRawFormParams
import pimonitor.client.business.utils.disbursements.DisbursementRawFormParams
import pimonitor.core.business.interventions.Intervention
import presenters.cases.CrowdState

open class BusinessInterventionsScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : UIScope<CrowdState<Intervention>> {
    override val viewModel by lazy { BusinessInterventionsViewModel(config) }

    val loadAllInterventions = { businessId: String ->
        viewModel.post(LoadAllInterventions(businessId))
    }

    val showCreateInterventionForm = { businessId: String ->
        viewModel.post(ShowCreateInterventionForm(businessId))
    }

    val sendCreateInterventionForm = { params: CreateInterventionRawFormParams ->
        viewModel.post(SendCreateInterventionForm(params))
    }

    val showCreateDisbursementForm = { intervention: Intervention ->
        viewModel.post(ShowCreateDisbursementForm(intervention))
    }

    val sendCreateDisbursementForm = { intervention: Intervention, params: DisbursementRawFormParams ->
        viewModel.post(SendCreateDisbursementForm(intervention, params))
    }

    val showCreateGoalForm = { intervention: Intervention ->
        viewModel.post(ShowCreateGoalForm(intervention))
    }

    val sendCreateGoalForm = { intervention: Intervention, params: CreateGoalRawFormParams ->
        viewModel.post(SendCreateGoalForm(intervention, params))
    }

    val exitDialog = {
        viewModel.post(ExitDialog)
    }
}