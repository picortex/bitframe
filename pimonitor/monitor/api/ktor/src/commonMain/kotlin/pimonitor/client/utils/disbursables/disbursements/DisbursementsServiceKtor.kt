package pimonitor.client.utils.disbursables.disbursements

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.Identified
import bitframe.core.RequestBody
import bitframe.core.RestPath
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import later.later
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import response.decodeResponseFromString

internal class DisbursementsServiceKtor(
    private val config: ServiceConfigKtor,
    private val path: RestPath
) : DisbursementsServiceImpl(config) {
    private val json get() = config.json
    private val http get() = config.http
    override fun create(params: RequestBody.Authorized<DisbursableDisbursementParams>) = config.scope.later {
        val res = http.post(path.create) {
            setBody(json.of(params))
        }
        json.decodeResponseFromString(Disbursement.serializer(), res.bodyAsText()).response()
    }

    override fun update(params: RequestBody.Authorized<Identified<DisbursableDisbursementParams>>) = config.scope.later {
        val res = http.post(path.update) {
            setBody(json.of(params))
        }
        json.decodeResponseFromString(Disbursement.serializer(), res.bodyAsText()).response()
    }

    override fun delete(params: RequestBody.Authorized<Identified<Array<out String>>>) = config.scope.later {
        val res = http.post(path.delete) {
            setBody(json.of(params))
        }
        json.decodeResponseFromString(ListSerializer(Disbursement.serializer()), res.bodyAsText()).response().toInteroperableList()
    }
}