package pimonitor.server.business.interventions

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.server.utils.pathV1

class BusinessInterventionsModule(
    val controller: BusinessInterventionsController
) : Module {
    override val name: String = "Business Interventions Module"
    val path get() = controller.service.config.pathV1
    override val actions: List<Action> = listOf(
        Action("Create an investment", mapOf(), HttpRoute(HttpMethod.Post, path.businessInterventionsCreate) {
            controller.create(it)
        })
    )
}