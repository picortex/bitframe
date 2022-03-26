package pimonitor.server.businesses

import akkounts.reports.incomestatement.IncomeStatement
import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.HttpResponse
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString
import later.BaseLater.Companion.resolve
import later.await
import pimonitor.core.business.params.LoadReportParams
import pimonitor.core.businesses.BusinessFilter
import pimonitor.core.businesses.BusinessesDaodService
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.invites.InfoResults
import response.response

class BusinessController(
    val service: BusinessesDaodService
) {
    private val json get() = service.config.json

    suspend fun create(req: HttpRequest): HttpResponse = response {
        val rb = json.decodeFromString<RequestBody.Authorized<CreateMonitoredBusinessParams>>(req.compulsoryBody())
        resolve(service.create(rb).await())
    }.toHttpResponse()

    suspend fun all(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<BusinessFilter>>(req.compulsoryBody())
        resolve(service.all(rb).await())
    }.toHttpResponse()

    suspend fun delete(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<Array<String>>>(req.compulsoryBody())
        resolve(service.delete(rb).await())
    }.toHttpResponse()

    suspend fun dashboard(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<LoadReportParams>>(req.compulsoryBody())
        resolve(service.operationalDashboard(rb).await())
    }.toHttpResponse()

    suspend fun incomeStatement(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<String>>(req.compulsoryBody())
        resolve(service.incomeStatement(rb).await())
    }.toHttpResponse(InfoResults.serializer(IncomeStatement.serializer()))

    suspend fun balanceSheet(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<String>>(req.compulsoryBody())
        resolve(service.balanceSheet(rb).await())
    }.toHttpResponse()

    suspend fun load(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<String>>(req.compulsoryBody())
        resolve(service.load(rb).await())
    }.toHttpResponse()

    suspend fun availableReports(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<String>>(req.compulsoryBody())
        resolve(service.availableReports(rb).await())
    }.toHttpResponse()
}