package pimonitor.client.investments

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.client.logger
import bitframe.core.RequestBody
import later.await
import later.later
import pimonitor.core.investments.InvestmentFilter
import pimonitor.core.investments.InvestmentsServiceCore

abstract class InvestmentsService(private val config: ServiceConfig) : InvestmentsServiceCore {
    val logger by config.logger(withSessionInfo = true)

    fun delete(vararg ids: String) = config.scope.later {
        logger.info("Deleting investments(${ids.joinToString(",")}")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("delete multiple investments"),
            data = ids
        )
        delete(rb).await()
    }

    fun all() = config.scope.later {
        logger.info("Loading all investments")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load all investments"),
            data = InvestmentFilter()
        )
        all(rb).await()
    }
}