@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.interventions

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.client.logger
import bitframe.core.IdentifiedRaw
import bitframe.core.RequestBody
import bitframe.core.toValidated
import later.Later
import later.await
import later.later
import pimonitor.client.utils.disbursables.DisbursableServiceImpl
import pimonitor.core.interventions.Intervention
import pimonitor.core.interventions.InterventionSummary
import pimonitor.core.interventions.InterventionsServiceCore
import pimonitor.core.interventions.params.InterventionRawParams
import pimonitor.core.interventions.params.toValidatedParams
import kotlin.js.JsExport

abstract class InterventionService(
    private val config: ServiceConfig
) : DisbursableServiceImpl<Intervention, InterventionSummary>(config), InterventionsServiceCore {

    private val logger by config.logger(withSessionInfo = true)

    fun create(params: InterventionRawParams): Later<Intervention> = config.scope.later {
        logger.info("Creating intervention)")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("create an intervention"),
            data = params.toValidatedParams()
        )
        create(rb).await().also { logger.info("Intervention created successfully") }
    }

    fun update(params: IdentifiedRaw<InterventionRawParams>): Later<Intervention> = config.scope.later {
        logger.info("Updating intervention")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("update an intervention"),
            data = params.toValidated { it.toValidatedParams() }
        )
        update(rb).await().also { logger.info("Success") }
    }
}