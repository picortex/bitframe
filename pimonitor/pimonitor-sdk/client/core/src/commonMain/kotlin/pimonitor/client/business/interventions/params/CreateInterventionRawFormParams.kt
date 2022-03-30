package pimonitor.client.business.interventions.params

import kotlin.js.JsExport

@JsExport
interface CreateInterventionRawFormParams {
    val name: String
    val interventionDate: String
    val interventionDeadline: String
    val amount: String
    val recommendations: String
}