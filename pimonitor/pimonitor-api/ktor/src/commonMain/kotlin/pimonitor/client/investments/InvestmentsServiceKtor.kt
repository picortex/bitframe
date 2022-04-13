package pimonitor.client.investments

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.Identified
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.business.utils.disbursements.Disbursement
import pimonitor.core.investments.Investment
import pimonitor.core.investments.InvestmentFilter
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.params.InvestmentDisbursementParams
import pimonitor.core.investments.params.InvestmentsParams
import response.decodeResponseFromString

class InvestmentsServiceKtor(private val config: ServiceConfigKtor) : InvestmentsService(config) {
    private val json get() = config.json
    private val http get() = config.http
    private val path get() = config.pathV1

    override fun update(rb: RequestBody.Authorized<Identified<InvestmentsParams>>): Later<Investment> = config.scope.later {
        val res = http.post(path.investmentsUpdate) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Investment.serializer(), res.bodyAsText()).response()
    }

    override fun delete(rb: RequestBody.Authorized<Array<out String>>): Later<List<Investment>> = config.scope.later {
        val res = http.post(path.investmentsDelete) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(ListSerializer(Investment.serializer()), res.bodyAsText()).response().toInteroperableList()
    }

    override fun all(rb: RequestBody.Authorized<InvestmentFilter>): Later<List<InvestmentSummary>> = config.scope.later {
        val res = http.post(path.investmentsAll) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(ListSerializer(InvestmentSummary.serializer()), res.bodyAsText()).response().toInteroperableList()
    }

    override fun create(rb: RequestBody.Authorized<InvestmentsParams>): Later<Investment> = config.scope.later {
        val res = http.post(path.investmentsCreate) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Investment.serializer(), res.bodyAsText()).response()
    }

    override fun disburse(rb: RequestBody.Authorized<InvestmentDisbursementParams>): Later<Disbursement> = config.scope.later {
        val res = http.post(path.investmentsDisbursementCreate) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Disbursement.serializer(), res.bodyAsText()).response()
    }
}