package pimonitor.server.interventions

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.core.interventions.Intervention
import pimonitor.core.interventions.InterventionSummary
import pimonitor.core.investments.Investment
import pimonitor.core.investments.InvestmentSummary
import pimonitor.server.disbursables.DisbursablesModule
import pimonitor.server.utils.pathV1

class InterventionsModule(
    override val controller: InterventionsController
) : DisbursablesModule<Intervention, InterventionSummary>(
    controller, Intervention.serializer(), InterventionSummary.serializer(), controller.service.config.pathV1.interventions, "intervention"
), Module {
    override val name: String = "Interventions"
    override val actions: List<Action> = listOf(
        Action("Create Intervention", mapOf(), HttpRoute(HttpMethod.Post, path.create) {
            controller.createIntervention(it)
        }),
        Action("Update Intervention", mapOf(), HttpRoute(HttpMethod.Post, path.update) {
            controller.updateIntervention(it)
        })
    ) + disbursableActions
}