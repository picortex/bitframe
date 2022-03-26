@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.financials

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.client.logger
import bitframe.core.RequestBody
import later.await
import later.later
import pimonitor.core.business.financials.BusinessFinancialsServiceCore
import kotlin.js.JsExport

abstract class BusinessFinancialsService(
    private val config: ServiceConfig
) : BusinessFinancialsServiceCore {

    val logger by config.logger(withSessionInfo = true)

    fun availableReports(businessId: String) = config.scope.later {
        logger.info("fetching available reports for business(uid=$businessId)")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load available reports"),
            data = businessId
        )
        availableReports(rb).await().also { logger.info("Reports found"); }
    }

    fun incomeStatement(businessId: String) = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load income statement"),
            data = businessId
        )
        incomeStatement(rb).await()
    }

    fun balanceSheet(businessId: String) = config.scope.later {
        logger.info("Loading balance sheet for business(uid=$businessId)")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load income statement"),
            data = businessId
        )
        balanceSheet(rb).await().also { logger.info("Loaded balance sheet: $it") }
    }
}