package pimonitor.server.business.investments

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.business.investments.BusinessInvestmentsServiceDaod
import pimonitor.core.business.investments.CreateInvestmentsParams
import response.response

class BusinessInvestmentsController(
    val service: BusinessInvestmentsServiceDaod
) {
    private val json get() = service.config.json

    suspend fun capture(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<CreateInvestmentsParams>>(req.compulsoryBody())
        resolve(service.capture(rb).await())
    }.toHttpResponse()

    suspend fun all(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<String>>(req.compulsoryBody())
        resolve(service.all(rb).await())
    }.toHttpResponse()
}