package pimonitor.client.portfolio

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import later.later
import pimonitor.core.portfolio.PortfolioData
import pimonitor.core.portfolio.PortfolioFilter
import response.decodeResponseFromString

class PortfolioServiceKtor(
    override val config: ServiceConfigKtor
) : PortfolioService {
    val client get() = config.http
    val json get() = config.json
    val basePath = "/api/portfolio"
    override fun load(rb: RequestBody.Authorized<PortfolioFilter>) = config.scope.later {
        val res = client.post("${config.url}$basePath/load") {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(PortfolioData.serializer(), res.bodyAsText()).response()
    }
}