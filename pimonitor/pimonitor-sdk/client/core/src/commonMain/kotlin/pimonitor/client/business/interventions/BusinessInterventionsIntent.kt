package pimonitor.client.business.interventions

import pimonitor.client.business.interventions.params.CreateGoalRawFormParams
import pimonitor.client.business.interventions.params.CreateInterventionRawFormParams
import pimonitor.client.business.utils.disbursements.CreateDisbursementRawFormParams
import pimonitor.core.business.interventions.Intervention

sealed class BusinessInterventionsIntent {
    data class LoadAllInterventions(val businessId: String) : BusinessInterventionsIntent()

    data class ShowCreateInterventionForm(val businessId: String) : BusinessInterventionsIntent()
    data class SendCreateInterventionForm(val params: CreateInterventionRawFormParams) : BusinessInterventionsIntent()

    data class ShowCreateDisbursementForm(val intervention: Intervention) : BusinessInterventionsIntent()
    data class SendCreateDisbursementForm(val intervention: Intervention, val params: CreateDisbursementRawFormParams) : BusinessInterventionsIntent()

    data class ShowCreateGoalForm(val intervention: Intervention) : BusinessInterventionsIntent()
    data class SendCreateGoalForm(val intervention: Intervention, val params: CreateGoalRawFormParams) : BusinessInterventionsIntent()

    object ExitDialog : BusinessInterventionsIntent()
}
