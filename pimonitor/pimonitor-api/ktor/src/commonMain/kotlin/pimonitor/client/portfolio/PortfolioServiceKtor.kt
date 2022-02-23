package pimonitor.client.portfolio

import bitframe.client.KtorServiceConfig
import bitframe.core.RequestBody
import later.Later
import pimonitor.core.portfolio.PortfolioData
import pimonitor.core.portfolio.PortfolioFilter

class PortfolioServiceKtor(
    override val config: KtorServiceConfig
) : PortfolioService {
    override fun load(rb: RequestBody.Authorized<PortfolioFilter>): Later<PortfolioData> {
        TODO("Not yet implemented")
    }
}