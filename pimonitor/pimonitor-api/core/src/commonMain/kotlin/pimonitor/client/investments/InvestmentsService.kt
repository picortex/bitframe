@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.investments

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.client.logger
import bitframe.core.IdentifiedRaw
import bitframe.core.RequestBody
import bitframe.core.toValidated
import kotlinx.collections.interoperable.List
import later.Later
import later.await
import later.later
import pimonitor.client.utils.DisbursableService
import pimonitor.client.utils.DisbursableServiceImpl
import pimonitor.core.investments.Investment
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.InvestmentsServiceCore
import pimonitor.core.investments.filters.InvestmentRawFilter
import pimonitor.core.investments.filters.toValidatedFilter
import pimonitor.core.investments.params.InvestmentRawParams
import pimonitor.core.investments.params.toValidatedParams
import pimonitor.core.utils.disbursements.Disbursement
import pimonitor.core.utils.disbursements.params.DisbursableDisbursementParams
import pimonitor.core.utils.disbursements.params.DisbursableDisbursementRawParams
import pimonitor.core.utils.disbursements.params.toValidatedParams
import kotlin.js.JsExport

abstract class InvestmentsService(private val config: ServiceConfig) : DisbursableServiceImpl<Investment>(config), InvestmentsServiceCore {
    val logger by config.logger(withSessionInfo = true)

    fun create(params: InvestmentRawParams) = config.scope.later {
        logger.info("Capturing ${params.name} investment")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("capture investments"),
            data = params.toValidatedParams()
        )
        create(rb).await().also { logger.info("Investment captured successfully") }
    }

    fun update(params: IdentifiedRaw<InvestmentRawParams>) = config.scope.later {
        logger.info("Updating ${params.body.name} investment")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("updated an investment"),
            data = params.toValidated().map { it.toValidatedParams() }
        )
        update(rb).await()
    }

    fun delete(vararg ids: String) = config.scope.later {
        logger.info("Deleting investments(${ids.joinToString(",")}")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("delete multiple investments"),
            data = ids
        )
        delete(rb).await().also { logger.info("Success") }
    }

    fun all(params: InvestmentRawFilter? = null) = config.scope.later {
        logger.info("Loading all investments")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load all investments"),
            data = params.toValidatedFilter()
        )
        all(rb).await()
    }
}