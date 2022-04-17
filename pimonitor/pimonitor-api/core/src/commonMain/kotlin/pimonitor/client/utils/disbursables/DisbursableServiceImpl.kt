@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.utils.disbursables

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
import logging.Logger
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.DisbursableServiceCore
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementRawParams
import pimonitor.core.utils.disbursables.disbursements.params.toValidatedParams
import kotlin.js.JsExport

abstract class DisbursableServiceImpl<out D : Disbursable>(private val config: ServiceConfig) : DisbursableService<D>, DisbursableServiceCore<D> {
    private val logger: Logger by config.logger(withSessionInfo = true)
    override fun load(disbursableId: String): Later<D> = config.scope.later {
        logger.info("loading a disbursable(uid=$disbursableId)")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("update a disbursement"),
            data = disbursableId
        )
        load(rb).await().also { logger.info("Success") }
    }

    override fun createDisbursement(params: DisbursableDisbursementParams): Later<Disbursement> = config.scope.later {
        logger.info("Creating a disbursement")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("create a disbursement"),
            data = params.toValidatedParams()
        )
        createDisbursement(rb).await()
    }

    override fun updateDisbursement(params: IdentifiedRaw<DisbursableDisbursementRawParams>): Later<Disbursement> = config.scope.later {
        logger.info("Updating a disbursement")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("update a disbursement"),
            data = params.toValidated { it.toValidatedParams() }
        )
        updateDisbursement(rb).await().also { logger.info("Success") }
    }

    override fun deleteDisbursements(params: IdentifiedRaw<Array<String>>): Later<List<Disbursement>> = config.scope.later {
        logger.info("Deleting disbursements")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("delete disbursement"),
            data = params.toValidated()
        )
        deleteDisbursement(rb).await()
    }
}