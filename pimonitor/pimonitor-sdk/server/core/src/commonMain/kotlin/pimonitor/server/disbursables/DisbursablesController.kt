package pimonitor.server.disbursables

import bitframe.core.Identified
import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.DisbursableServiceDaod
import pimonitor.core.utils.disbursables.DisbursableSummary
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import pimonitor.core.utils.disbursables.filters.DisbursableFilter
import response.response

open class DisbursablesController<out D : Disbursable, out DS : DisbursableSummary>(
    open val service: DisbursableServiceDaod<D, DS>,
    private val serializer: KSerializer<@UnsafeVariance D>,
    private val summarySerializer: KSerializer<@UnsafeVariance DS>
) {
    private val json get() = service.config.json
    suspend fun load(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<String>>(req.compulsoryBody())
        resolve(service.load(rb).await().also { println(it) })
    }.toHttpResponse(summarySerializer)

    suspend fun createDisbursement(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<DisbursableDisbursementParams>>(req.compulsoryBody())
        resolve(service.createDisbursement(rb).await())
    }.toHttpResponse()

    suspend fun deleteDisbursement(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<Identified<Array<String>>>>(req.compulsoryBody())
        resolve(service.deleteDisbursement(rb).await())
    }.toHttpResponse()

    suspend fun updateDisbursement(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<Identified<DisbursableDisbursementParams>>>(req.compulsoryBody())
        resolve(service.updateDisbursement(rb).await())
    }.toHttpResponse()

    suspend fun all(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<DisbursableFilter>>(req.compulsoryBody())
        resolve(service.all(rb).await())
    }.toHttpResponse(ListSerializer(summarySerializer))

    suspend fun delete(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<Array<String>>>(req.compulsoryBody())
        resolve(service.delete(rb).await())
    }.toHttpResponse(ListSerializer(serializer))
}