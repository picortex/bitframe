package pimonitor.server.business.financials

import akkounts.reports.incomestatement.IncomeStatement
import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.business.financials.BusinessFinancialsServiceDaod
import pimonitor.core.invites.InfoResults
import response.response

class BusinessFinancialsController(
    val service: BusinessFinancialsServiceDaod
) {
    private val json get() = service.config.json

    suspend fun availableReports(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<String>>(req.compulsoryBody())
        resolve(service.availableReports(rb).await())
    }.toHttpResponse()

    suspend fun incomeStatement(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<String>>(req.compulsoryBody())
        resolve(service.incomeStatement(rb).await())
    }.toHttpResponse(InfoResults.serializer(IncomeStatement.serializer()))

    suspend fun balanceSheet(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<String>>(req.compulsoryBody())
        resolve(service.balanceSheet(rb).await())
    }.toHttpResponse()
}