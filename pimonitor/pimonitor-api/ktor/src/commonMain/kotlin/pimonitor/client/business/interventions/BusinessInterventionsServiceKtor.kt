package pimonitor.client.business.interventions

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.business.interventions.Intervention
import pimonitor.core.business.interventions.params.CreateGoalParams
import pimonitor.core.business.interventions.params.CreateInterventionDisbursementParams
import pimonitor.core.business.interventions.params.InterventionParams
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import response.decodeResponseFromString

open class BusinessInterventionsServiceKtor(
    private val config: ServiceConfigKtor
) : BusinessInterventionService(config) {
    private val path get() = config.pathV1
    private val client get() = config.http
    private val json get() = config.json

    override fun create(rb: RequestBody.Authorized<InterventionParams>) = config.scope.later {
        val res = client.post(path.businessInterventionsCreate) { setBody(json.of(rb)) }
        json.decodeResponseFromString(Intervention.serializer(), res.bodyAsText()).response()
    }

    override fun all(rb: RequestBody.Authorized<String>): Later<List<Intervention>> = config.scope.later {
        val res = client.post(path.businessInterventionsAll) { setBody(json.of(rb)) }
        json.decodeResponseFromString(ListSerializer(Intervention.serializer()), res.bodyAsText()).response().toInteroperableList()
    }

    override fun disburse(rb: RequestBody.Authorized<CreateInterventionDisbursementParams>) = config.scope.later {
        val res = client.post(path.businessInterventionsDisburse) { setBody(json.of(rb)) }
        json.decodeResponseFromString(Disbursement.serializer(), res.bodyAsText()).response()
    }

    override fun addGoal(rb: RequestBody.Authorized<CreateGoalParams>): Later<Any> {
        TODO("Not yet implemented")
    }
}