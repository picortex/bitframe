package pimonitor.client.portfolio

import bitframe.client.ServiceConfig
import bitframe.core.RequestBody
import bitframe.core.Session
import later.await
import later.later
import pimonitor.core.portfolio.PortfolioFilter
import pimonitor.core.portfolio.PortfolioServiceCore

interface PortfolioService : PortfolioServiceCore {
    override val config: ServiceConfig

    fun load() = scope.later {
        val rb = RequestBody.Authorized(
            session = config.session.value as? Session.SignedIn ?: error("You must be logged in to load portfolio data"),
            data = PortfolioFilter("")
        )
        load(rb).await()
    }
}