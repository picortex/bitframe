package pimonitor.core.interventions.params

import kotlin.js.JsExport

@JsExport
interface CreateGoalRawParams {
    val name: String
    val item: String
    val current: String
    val target: String
    val deadline: Double
}