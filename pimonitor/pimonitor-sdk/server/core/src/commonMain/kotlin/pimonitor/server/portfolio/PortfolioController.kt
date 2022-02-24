package pimonitor.server.portfolio

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.portfolio.PortfolioDaodService
import pimonitor.core.portfolio.PortfolioFilter
import response.response

class PortfolioController(
    private val service: PortfolioDaodService
) {
    private val json get() = service.config.json
    suspend fun load(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<PortfolioFilter>>(req.compulsoryBody())
        resolve(service.load(rb).await())
    }.toHttpResponse()
}