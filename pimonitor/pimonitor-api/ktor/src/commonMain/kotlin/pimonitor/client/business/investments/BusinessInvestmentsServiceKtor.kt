package pimonitor.client.business.investments

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.investments.Investment
import pimonitor.core.investments.params.InvestmentDisbursementParams
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import response.decodeResponseFromString

open class BusinessInvestmentsServiceKtor(
    private val config: ServiceConfigKtor
) : BusinessInvestmentsService(config) {
    private val path get() = config.pathV1
    private val client get() = config.http
    private val json get() = config.json
    override fun capture(rb: RequestBody.Authorized<InvestmentParams>) = config.scope.later {
        val res = client.post(path.businessInvestmentsCapture) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Investment.serializer(), res.bodyAsText()).response()
    }

    override fun all(rb: RequestBody.Authorized<String>) = config.scope.later {
        val res = client.post(path.businessInvestmentsAll) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(ListSerializer(Investment.serializer()), res.bodyAsText()).response().toInteroperableList()
    }

    override fun disburse(rb: RequestBody.Authorized<InvestmentDisbursementParams>) = config.scope.later {
        val res = client.post(path.businessInvestmentsDisburse) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Disbursement.serializer(), res.bodyAsText()).response()
    }
}