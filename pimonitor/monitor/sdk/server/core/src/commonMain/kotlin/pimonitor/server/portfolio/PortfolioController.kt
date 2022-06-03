package pimonitor.server.portfolio

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.portfolio.PortfolioServiceDaod
import pimonitor.core.portfolio.PortfolioFilter
import response.response

class PortfolioController(
    private val service: PortfolioServiceDaod
) {
    private val json get() = service.config.json
    internal val config get() = service.config
    suspend fun load(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<PortfolioFilter>>(req.compulsoryBody())
        resolve(service.load(rb).await())
    }.toHttpResponse()
}