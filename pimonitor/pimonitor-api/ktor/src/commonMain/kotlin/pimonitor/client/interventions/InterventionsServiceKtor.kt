package pimonitor.client.interventions

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.Identified
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.List
import later.Later
import later.later
import pimonitor.client.utils.disbursables.DisbursableServiceKtor
import pimonitor.client.utils.disbursables.disbursements.DisbursementsService
import pimonitor.client.utils.disbursables.disbursements.DisbursementsServiceKtor
import pimonitor.client.utils.pathV1
import pimonitor.core.interventions.Goal
import pimonitor.core.interventions.Intervention
import pimonitor.core.interventions.InterventionSummary
import pimonitor.core.interventions.params.InterventionParams
import pimonitor.core.utils.disbursables.DisbursableServiceCore
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import response.decodeResponseFromString

class InterventionsServiceKtor(
    private val config: ServiceConfigKtor
) : InterventionService(config), DisbursableServiceCore<Intervention, InterventionSummary> by DisbursableServiceKtor(
    config, Intervention.serializer(), InterventionSummary.serializer(), config.pathV1.interventions
) {
    private val json get() = config.json
    private val http get() = config.http
    private val path get() = config.pathV1

    override val disbursements: DisbursementsService by lazy { DisbursementsServiceKtor(config, path.interventions.disbursements) }

    override fun create(rb: RequestBody.Authorized<InterventionParams>): Later<Intervention> = config.scope.later {
        val res = http.post(path.interventions.create) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Intervention.serializer(), res.bodyAsText()).response()
    }

    override fun update(rb: RequestBody.Authorized<Identified<InterventionParams>>): Later<Intervention> = config.scope.later {
        val res = http.post(path.interventions.update) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Intervention.serializer(), res.bodyAsText()).response()
    }

    override fun createGoal(rb: RequestBody.Authorized<DisbursableDisbursementParams>): Later<Goal> {
        TODO("Not yet implemented")
    }

    override fun deleteGoal(rb: RequestBody.Authorized<Identified<Array<String>>>): Later<List<Goal>> {
        TODO("Not yet implemented")
    }

    override fun updateGoal(rb: RequestBody.Authorized<Identified<DisbursableDisbursementParams>>): Later<Goal> {
        TODO("Not yet implemented")
    }
}