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
import pimonitor.client.utils.disbursements.DisbursableServiceKtor
import pimonitor.client.utils.pathV1
import pimonitor.core.investments.Investment
import pimonitor.core.investments.filters.InvestmentFilter
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.utils.disbursements.DisbursableServiceCore
import response.decodeResponseFromString

class InvestmentsServiceKtor(
    private val config: ServiceConfigKtor
) : InvestmentsService(config), DisbursableServiceCore<Investment> by DisbursableServiceKtor(config, Investment.serializer(), config.pathV1.investments) {
    private val json get() = config.json
    private val http get() = config.http
    private val path get() = config.pathV1

    override fun update(rb: RequestBody.Authorized<Identified<InvestmentParams>>): Later<Investment> = config.scope.later {
        val res = http.post(path.investments.update) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Investment.serializer(), res.bodyAsText()).response()
    }

    override fun delete(rb: RequestBody.Authorized<Array<out String>>): Later<List<Investment>> = config.scope.later {
        val res = http.post(path.investments.delete) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(ListSerializer(Investment.serializer()), res.bodyAsText()).response().toInteroperableList()
    }

    override fun all(rb: RequestBody.Authorized<InvestmentFilter>): Later<List<InvestmentSummary>> = config.scope.later {
        val res = http.post(path.investments.all) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(ListSerializer(InvestmentSummary.serializer()), res.bodyAsText()).response().toInteroperableList()
    }

    override fun create(rb: RequestBody.Authorized<InvestmentParams>): Later<Investment> = config.scope.later {
        val res = http.post(path.investments.create) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Investment.serializer(), res.bodyAsText()).response()
    }
}