package pimonitor.server.business.investments

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import pimonitor.core.investments.params.InvestmentDisbursementParams
import pimonitor.core.investments.params.InvestmentParams
import response.response

class BusinessInvestmentsController(
    val service: BusinessInvestmentsServiceDaod
) {
    private val json get() = service.config.json

    suspend fun capture(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<InvestmentParams>>(req.compulsoryBody())
        resolve(service.capture(rb).await())
    }.toHttpResponse()

    suspend fun all(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<String>>(req.compulsoryBody())
        resolve(service.all(rb).await())
    }.toHttpResponse()

    suspend fun disburse(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<InvestmentDisbursementParams>>(req.compulsoryBody())
        resolve(service.disburse(rb).await())
    }.toHttpResponse()
}