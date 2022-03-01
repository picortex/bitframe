package pimonitor.server.search

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.search.SearchDaodService
import pimonitor.core.search.SearchParams
import response.response

class SearchController(val service: SearchDaodService) {
    val config get() = service.config
    val json get() = config.json
    suspend fun search(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<SearchParams>>(req.compulsoryBody())
        resolve(service.search(rb).await())
    }.toHttpResponse()
}