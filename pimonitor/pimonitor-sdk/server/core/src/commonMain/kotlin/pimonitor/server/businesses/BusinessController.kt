package pimonitor.server.businesses

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.HttpResponse
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.businesses.BusinessFilter
import pimonitor.core.businesses.BusinessesDaodService
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import response.response

class BusinessController(
    val service: BusinessesDaodService
) {
    val json get() = service.config.json
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
}