package pimonitor.server.business.operations

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.business.operations.BusinessOperationsServiceDaod
import pimonitor.core.business.utils.params.LoadReportParams
import response.response

class BusinessOperationsController(
    val service: BusinessOperationsServiceDaod
) {
    val json get() = service.config.json
    suspend fun dashboard(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<LoadReportParams>>(req.compulsoryBody())
        resolve(service.dashboard(rb).await())
    }.toHttpResponse()
}