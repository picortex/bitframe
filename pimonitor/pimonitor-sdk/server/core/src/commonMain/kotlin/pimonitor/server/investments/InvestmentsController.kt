package pimonitor.server.investments

import bitframe.core.Identified
import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.investments.Investment
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.filters.InvestmentFilter
import pimonitor.core.investments.InvestmentsServiceDaod
import pimonitor.core.investments.params.InvestmentDisbursementParams
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.server.disbursables.DisbursablesController
import response.response

class InvestmentsController(
    override val service: InvestmentsServiceDaod
) : DisbursablesController<Investment, InvestmentSummary>(
    service, Investment.serializer(), InvestmentSummary.serializer()
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
}