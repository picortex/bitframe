package pimonitor.server.business.overview

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.business.overview.BusinessOverviewServiceDaod
import pimonitor.core.business.utils.info.LoadInfoParsedParams
import response.response

class BusinessOverviewController(
    val service: BusinessOverviewServiceDaod
) {
    private val json get() = service.config.json
    suspend fun load(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<LoadInfoParsedParams>>(req.compulsoryBody())
        resolve(service.load(rb).await())
    }.toHttpResponse()
}