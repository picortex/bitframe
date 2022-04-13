@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.investments

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.client.logger
import bitframe.core.RequestBody
import later.await
import later.later
import pimonitor.core.investments.BusinessInvestmentsServiceCore
import pimonitor.core.investments.params.*
import kotlin.js.JsExport

abstract class BusinessInvestmentsService(
    private val config: ServiceConfig
) : BusinessInvestmentsServiceCore {

    private val logger by config.logger(withSessionInfo = true)

    fun capture(params: CreateInvestmentsRawParams) = config.scope.later {
        logger.info("Capturing investment")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("capture investments"),
            data = params.toValidatedParams()
        )
        capture(rb).await().also { logger.info("Investment captured successfully") }
    }

    fun disburse(params: CreateInvestmentDisbursementRawParams) = config.scope.later {
        logger.info("Creating a disbursement")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("create a disbursement"),
            data = params.toValidatedParams()
        )
        disburse(rb).await()
    }

    fun all(businessId: String) = config.scope.later {
        logger.info("Loading investments")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load investments for business"),
            data = businessId
        )
        all(rb).await().also { logger.info("Investment loaded successfully") }
    }
}