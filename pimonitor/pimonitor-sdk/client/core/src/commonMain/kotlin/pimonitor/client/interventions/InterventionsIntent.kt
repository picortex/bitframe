package pimonitor.client.interventions

import pimonitor.client.interventions.params.GoalRawParams
import pimonitor.client.utils.disbursables.DisbursablesIntent
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.interventions.InterventionSummary
import pimonitor.core.interventions.params.InterventionRawParams

sealed class InterventionsIntent : DisbursablesIntent {
    data class ShowCreateInterventionForm(val business: MonitoredBusinessBasicInfo?, val params: InterventionRawParams?) : InterventionsIntent()
    data class SendCreateInterventionForm(val params: InterventionRawParams) : InterventionsIntent()

    data class ShowUpdateInterventionForm(val intervention: InterventionSummary, val params: InterventionRawParams?) : InterventionsIntent()
    data class SendUpdateInterventionForm(val intervention: InterventionSummary, val params: InterventionRawParams) : InterventionsIntent()

    data class ShowCreateGoalForm(val intervention: InterventionSummary, val params: GoalRawParams?) : InterventionsIntent()
    data class SendCreateGoalForm(val intervention: InterventionSummary, val params: GoalRawParams) : InterventionsIntent()

    data class ShowUpdateGoalForm(val intervention: InterventionSummary, val params: GoalRawParams?) : InterventionsIntent()
    data class SendUpdateGoalForm(val intervention: InterventionSummary, val params: GoalRawParams) : InterventionsIntent()
}
