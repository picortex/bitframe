@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.investments

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.client.logger
import bitframe.core.RequestBody
import later.await
import later.later
import pimonitor.core.business.investments.BusinessInvestmentsServiceCore
import pimonitor.core.business.investments.params.*
import kotlin.js.JsExport

abstract class BusinessInvestmentsService(
    private val config: ServiceConfig
) : BusinessInvestmentsServiceCore {

    private val logger by config.logger(withSessionInfo = true)

    fun capture(params: CreateInvestmentsRawParams) = config.scope.later {
        logger.info("Capturing investment")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("capture investments"),
            data = params.toValidatedCreateInvestmentsParams()
        )
        capture(rb).await().also { logger.info("Investment captured successfully") }
    }

    fun all(businessId: String) = config.scope.later {
        logger.info("Loading investments")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load investments for business"),
            data = businessId
        )
        all(rb).await().also { logger.info("Investment loaded successfully") }
    }

    fun disburse(params: CreateInvestmentDisbursementRawParams) = config.scope.later {
        logger.info("Creating a disbursement")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("create a disbursement"),
            data = params.toValidatedParams()
        )
        disburse(rb).await()
    }
}