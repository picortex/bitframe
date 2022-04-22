package pimonitor.client.interventions.params

import kotlin.js.JsExport

@JsExport
interface GoalRawParams {
    val name: String
    val item: String
    val current: String
    val target: String
    val deadline: String
}