@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.interventions

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.client.logger
import bitframe.core.RequestBody
import later.await
import later.later
import pimonitor.core.business.interventions.BusinessInterventionsServiceCore
import pimonitor.core.business.interventions.params.CreateInterventionDisbursementRawParams
import pimonitor.core.business.interventions.params.InterventionRawParams
import pimonitor.core.business.interventions.params.toValidatedParams
import kotlin.js.JsExport

abstract class BusinessInterventionService(
    private val config: ServiceConfig
) : BusinessInterventionsServiceCore {

    private val logger by config.logger(withSessionInfo = true)

    fun create(params: InterventionRawParams) = config.scope.later {
        logger.info("Creating intervention)")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("capture investments"),
            data = params.toValidatedParams()
        )
        create(rb).await().also { logger.info("Interventions created successfully") }
    }

    fun all(businessId: String) = config.scope.later {
        logger.info("Loading interventions")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load interventions for business"),
            data = businessId
        )
        all(rb).await().also { logger.info("Interventions loaded successfully") }
    }

    fun disburse(params: CreateInterventionDisbursementRawParams) = config.scope.later {
        logger.info("Creating a disbursement")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("create a disbursement"),
            data = params.toValidatedParams()
        )
        disburse(rb).await()
    }

    fun addGoal(params: Any) = config.scope.later {
        logger.info("Adding goal for investment")
        TODO()
    }
}