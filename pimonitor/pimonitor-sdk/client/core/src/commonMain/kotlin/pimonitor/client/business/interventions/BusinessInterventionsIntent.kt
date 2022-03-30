package pimonitor.client.business.interventions

import pimonitor.core.business.investments.Investment
import pimonitor.core.business.utils.disbursements.CreateDisbursementRawParamsContextual

sealed class BusinessInterventionsIntent {
    data class LoadAllInterventions(val businessId: String) : BusinessInterventionsIntent()

    data class ShowCreateInterventionForm(val businessId: String) : BusinessInterventionsIntent()
    data class SendCreateInterventionForm(val params: Any) : BusinessInterventionsIntent()

    data class ShowCreateDisbursementForm(val investment: Investment) : BusinessInterventionsIntent()
    data class SendCreateDisbursementForm(val params: CreateDisbursementRawParamsContextual) : BusinessInterventionsIntent()

    data class ShowCreateGoalForm(val interventions: Any) : BusinessInterventionsIntent()
    data class SendCreateGoalForm(val params: Any) : BusinessInterventionsIntent()

    object ExitDialog : BusinessInterventionsIntent()
}
