package pimonitor.client.business.interventions

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.List
import later.Later
import later.later
import pimonitor.client.business.investments.BusinessInvestmentsService
import pimonitor.client.utils.pathV1
import pimonitor.core.business.interventions.Intervention
import pimonitor.core.business.interventions.params.CreateInterventionDisbursementParams
import pimonitor.core.business.investments.Investment
import pimonitor.core.business.investments.params.CreateInvestmentDisbursementParams
import pimonitor.core.business.investments.params.CreateInvestmentsParams
import pimonitor.core.business.utils.disbursements.Disbursement
import response.decodeResponseFromString

open class BusinessInterventionsServiceKtor(
    private val config: ServiceConfigKtor
) : BusinessInterventionService(config) {
    val path get() = config.pathV1
    val client get() = config.http
    val json get() = config.json
//    override fun capture(rb: RequestBody.Authorized<CreateInvestmentsParams>) = config.scope.later {
//        val res = client.post(path.businessInvestmentsCapture) {
//            setBody(json.of(rb))
//        }
//        json.decodeResponseFromString(Investment.serializer(), res.bodyAsText()).response()
//    }

    override fun create(rb: RequestBody.Authorized<Any>): Later<Intervention> {
        TODO("Not yet implemented")
    }

    override fun all(rb: RequestBody.Authorized<String>): Later<List<Intervention>> {
        TODO("Not yet implemented")
    }

    override fun disburse(rb: RequestBody.Authorized<CreateInterventionDisbursementParams>): Later<Disbursement> {
        TODO("Not yet implemented")
    }

    override fun addGoal(rb: RequestBody.Authorized<Any>): Later<Any> {
        TODO("Not yet implemented")
    }
}