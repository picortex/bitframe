package pimonitor.server.business.operations

import bitframe.core.RequestBody
import bitframe.core.map
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.business.operations.BusinessOperationsServiceDaod
import pimonitor.core.business.utils.info.LoadInfoParams
import pimonitor.core.business.utils.info.toParsedParams
import response.response

class BusinessOperationsController(
    val service: BusinessOperationsServiceDaod
) {
    val json get() = service.config.json
    suspend fun dashboard(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<LoadInfoParams>>(req.compulsoryBody())
        resolve(service.dashboard(rb.map { it.toParsedParams() }).await())
    }.toHttpResponse()
}