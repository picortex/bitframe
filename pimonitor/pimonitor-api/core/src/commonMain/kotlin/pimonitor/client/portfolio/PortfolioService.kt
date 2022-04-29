@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.portfolio

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.core.RequestBody
import bitframe.core.Session
import bitframe.core.logger
import later.await
import later.later
import pimonitor.core.portfolio.PortfolioFilter
import pimonitor.core.portfolio.PortfolioServiceCore
import kotlin.js.JsExport

abstract class PortfolioService(
    private val config: ServiceConfig
) : PortfolioServiceCore {
    protected val logger by config.logger()
    fun load() = config.scope.later {
        logger.info("Loading portfolio data")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load portfolio data"),
            data = PortfolioFilter("")
        )
        load(rb).await()
    }
}