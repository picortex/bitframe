package pimonitor.server.investments

import bitframe.core.Identified
import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.investments.filters.InvestmentFilter
import pimonitor.core.investments.InvestmentsServiceDaod
import pimonitor.core.investments.params.InvestmentDisbursementParams
import pimonitor.core.investments.params.InvestmentParams
import response.response

class InvestmentsController(
    internal val service: InvestmentsServiceDaod
) {
    private val json get() = service.config.json
    suspend fun createInvestment(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<InvestmentParams>>(req.compulsoryBody())
        resolve(service.create(rb).await())
    }.toHttpResponse()

    suspend fun updateInvestment(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<Identified<InvestmentParams>>>(req.compulsoryBody())
        resolve(service.update(rb).await())
    }.toHttpResponse()

    suspend fun all(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<InvestmentFilter>>(req.compulsoryBody())
        resolve(service.all(rb).await())
    }.toHttpResponse()

    suspend fun createDisbursement(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<InvestmentDisbursementParams>>(req.compulsoryBody())
        resolve(service.createDisbursement(rb).await())
    }.toHttpResponse()

    suspend fun updateDisbursement(req: HttpRequest) = response {
        resolve(2)
        TODO()
    }.toHttpResponse()

    suspend fun deleteInvestment(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<Array<String>>>(req.compulsoryBody())
        resolve(service.delete(rb).await())
    }.toHttpResponse()
}