package pimonitor.client.business.interventions

import pimonitor.client.business.interventions.params.CreateGoalRawFormParams
import pimonitor.client.business.interventions.params.CreateInterventionRawFormParams
import pimonitor.core.business.interventions.Intervention
import pimonitor.core.utils.disbursables.disbursements.params.DisbursementRawParams

sealed class BusinessInterventionsIntent {
    data class LoadAllInterventions(val businessId: String) : BusinessInterventionsIntent()

    data class ShowCreateInterventionForm(val businessId: String) : BusinessInterventionsIntent()
    data class SendCreateInterventionForm(val params: CreateInterventionRawFormParams) : BusinessInterventionsIntent()

    data class ShowCreateDisbursementForm(val intervention: Intervention) : BusinessInterventionsIntent()
    data class SendCreateDisbursementForm(val intervention: Intervention, val params: DisbursementRawParams) : BusinessInterventionsIntent()

    data class ShowCreateGoalForm(val intervention: Intervention) : BusinessInterventionsIntent()
    data class SendCreateGoalForm(val intervention: Intervention, val params: CreateGoalRawFormParams) : BusinessInterventionsIntent()

    object ExitDialog : BusinessInterventionsIntent()
}
