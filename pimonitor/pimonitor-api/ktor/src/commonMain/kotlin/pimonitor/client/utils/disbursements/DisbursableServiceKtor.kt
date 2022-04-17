package pimonitor.client.utils.disbursements

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
import pimonitor.core.utils.disbursements.Disbursable
import pimonitor.core.utils.disbursements.DisbursableEndpoint
import pimonitor.core.utils.disbursements.DisbursableServiceCore
import pimonitor.core.utils.disbursements.Disbursement
import pimonitor.core.utils.disbursements.params.DisbursableDisbursementParams
import response.decodeResponseFromString

class DisbursableServiceKtor<out D : Disbursable>(
    private val config: ServiceConfigKtor,
    private val serializer: KSerializer<D>,
    private val path: DisbursableEndpoint
) : DisbursableServiceCore<D> {
    private val json get() = config.json
    private val http get() = config.http
    override fun load(rb: RequestBody.Authorized<String>): Later<D> = config.scope.later {
        val res = http.post(path.disbursementCreate) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(serializer, res.bodyAsText()).response()
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