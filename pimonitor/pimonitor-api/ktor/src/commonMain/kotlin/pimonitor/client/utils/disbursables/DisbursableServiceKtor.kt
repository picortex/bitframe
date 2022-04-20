package pimonitor.client.utils.disbursables

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.Identified
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.KSerializer
import later.Later
import later.later
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.DisbursableEndpoint
import pimonitor.core.utils.disbursables.DisbursableServiceCore
import pimonitor.core.utils.disbursables.DisbursableSummary
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import pimonitor.core.utils.disbursables.filters.DisbursableFilter
import response.decodeResponseFromString

class DisbursableServiceKtor<out D : Disbursable, out DS : DisbursableSummary>(
    private val config: ServiceConfigKtor,
    private val serializer: KSerializer<D>,
    private val summarySerializer: KSerializer<DS>,
    private val path: DisbursableEndpoint
) : DisbursableServiceCore<D, DS> {
    private val json get() = config.json
    private val http get() = config.http
    override fun load(rb: RequestBody.Authorized<String>): Later<D> = config.scope.later {
        val res = http.post(path.load) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(serializer, res.bodyAsText()).response()
    }

    override fun all(rb: RequestBody.Authorized<DisbursableFilter>): Later<List<DS>> = config.scope.later {
        val res = http.post(path.all) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(ListSerializer(summarySerializer), res.bodyAsText()).response().toInteroperableList()
    }

    override fun delete(rb: RequestBody.Authorized<Array<out String>>): Later<List<D>> = config.scope.later {
        val res = http.post(path.delete) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(ListSerializer(serializer), res.bodyAsText()).response().toInteroperableList()
    }

    override fun createDisbursement(rb: RequestBody.Authorized<DisbursableDisbursementParams>): Later<Disbursement> = config.scope.later {
        val res = http.post(path.disbursementCreate) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Disbursement.serializer(), res.bodyAsText()).response()
    }

    override fun deleteDisbursement(rb: RequestBody.Authorized<Identified<Array<String>>>): Later<List<Disbursement>> = config.scope.later {
        val res = http.post(path.disbursementDelete) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(ListSerializer(Disbursement.serializer()), res.bodyAsText()).response().toInteroperableList()
    }

    override fun updateDisbursement(rb: RequestBody.Authorized<Identified<DisbursableDisbursementParams>>): Later<Disbursement> = config.scope.later {
        val res = http.post(path.disbursementUpdate) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Disbursement.serializer(), res.bodyAsText()).response()
    }
}