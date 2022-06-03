package pimonitor.client.utils.disbursables.disbursements

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.client.logger
import bitframe.core.IdentifiedRaw
import bitframe.core.RequestBody
import bitframe.core.toValidated
import later.Later
import later.await
import later.later
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementRawParams
import pimonitor.core.utils.disbursables.disbursements.params.toValidatedParams

abstract class DisbursementsServiceImpl(
    private val config: ServiceConfig
) : DisbursementsService {
    private val logger by config.logger(withSessionInfo = true)

    override fun create(params: DisbursableDisbursementRawParams): Later<Disbursement> = config.scope.later {
        logger.info("Creating a disbursement")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("create a disbursement"),
            data = params.toValidatedParams()
        )
        create(rb).await().also { logger.info("Disbursement created successfully") }
    }

    override fun update(params: IdentifiedRaw<DisbursableDisbursementRawParams>): Later<Disbursement> = config.scope.later {
        logger.info("Updating a disbursement")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("update a disbursement"),
            data = params.toValidated { it.toValidatedParams() }
        )
        update(rb).await().also { logger.info("Success") }
    }

    override fun delete(params: IdentifiedRaw<Array<String>>) = config.scope.later {
        logger.info("Deleting disbursements")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("delete disbursement"),
            data = params.toValidated()
        )
        delete(rb).await()
    }
}