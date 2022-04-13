package pimonitor.client.investments

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.client.logger
import bitframe.core.IdentifiedRaw
import bitframe.core.RequestBody
import bitframe.core.toValidated
import later.await
import later.later
import pimonitor.core.investments.InvestmentFilter
import pimonitor.core.investments.InvestmentsServiceCore
import pimonitor.core.investments.params.InvestmentDisbursementRawParams
import pimonitor.core.investments.params.InvestmentsRawParams
import pimonitor.core.investments.params.toValidatedParams

abstract class InvestmentsService(private val config: ServiceConfig) : InvestmentsServiceCore {
    val logger by config.logger(withSessionInfo = true)

    fun create(params: InvestmentsRawParams) = config.scope.later {
        logger.info("Capturing ${params.name} investment")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("capture investments"),
            data = params.toValidatedParams()
        )
        create(rb).await().also { logger.info("Investment captured successfully") }
    }

    fun update(params: IdentifiedRaw<InvestmentsRawParams>) = config.scope.later {
        logger.info("Updating ${params.body.name} investment")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("updated an investment"),
            data = params.toValidated().map { it.toValidatedParams() }
        )
        update(rb).await()
    }

    fun disburse(params: InvestmentDisbursementRawParams) = config.scope.later {
        logger.info("Creating a disbursement")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("create a disbursement"),
            data = params.toValidatedParams()
        )
        disburse(rb).await()
    }

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