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
import pimonitor.core.business.investments.Investment
import pimonitor.core.business.investments.params.CreateInvestmentDisbursementParams
import pimonitor.core.business.investments.params.CreateInvestmentsParams
import pimonitor.core.business.utils.disbursements.Disbursement
import response.decodeResponseFromString

open class BusinessInterventionsServiceKtor(
    private val config: ServiceConfigKtor
) : BusinessInvestmentsService(config) {
    val path get() = config.pathV1
    val client get() = config.http
    val json get() = config.json
    override fun capture(rb: RequestBody.Authorized<CreateInvestmentsParams>) = config.scope.later {
        val res = client.post(path.businessInvestmentsCapture) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Investment.serializer(), res.bodyAsText()).response()
    }

    override fun all(rb: RequestBody.Authorized<String>): Later<List<Investment>> {
        TODO("Not yet implemented")
    }

    override fun disburse(rb: RequestBody.Authorized<CreateInvestmentDisbursementParams>): Later<Disbursement> {
        TODO("Not yet implemented")
    }
}