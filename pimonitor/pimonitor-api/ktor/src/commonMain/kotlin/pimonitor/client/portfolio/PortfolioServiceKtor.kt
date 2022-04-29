package pimonitor.client.portfolio

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.portfolio.MonitoredBusinessPortfolio
import pimonitor.core.portfolio.PortfolioFilter
import response.decodeResponseFromString

class PortfolioServiceKtor(
    private val config: ServiceConfigKtor
) : PortfolioService(config) {
    private val client get() = config.http
    private val json get() = config.json
    override fun load(rb: RequestBody.Authorized<PortfolioFilter>) = config.scope.later {
        val res = client.post(config.pathV1.portfolioLoad) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(MonitoredBusinessPortfolio.serializer(), res.bodyAsText()).response()
    }
}