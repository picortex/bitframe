package pimonitor.client.business.interventions.params

import kotlin.js.JsExport

@JsExport
interface CreateGoalRawFormParams {
    val name: String
    val item: String
    val current: String
    val target: String
    val deadline: String
}