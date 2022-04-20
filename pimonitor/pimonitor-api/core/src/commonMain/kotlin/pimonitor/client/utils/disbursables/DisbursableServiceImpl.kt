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
import pimonitor.core.utils.disbursables.DisbursableSummary
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementRawParams
import pimonitor.core.utils.disbursables.disbursements.params.toValidatedParams
import pimonitor.core.utils.disbursables.filters.DisbursableFilter
import pimonitor.core.utils.disbursables.filters.DisbursableRawFilter
import pimonitor.core.utils.disbursables.filters.toValidateParams
import kotlin.js.JsExport

abstract class DisbursableServiceImpl<out D : Disbursable, out DS : DisbursableSummary>(
    private val config: ServiceConfig
) : DisbursableService<D, DS>, DisbursableServiceCore<D, DS> {
    private val logger: Logger by config.logger(withSessionInfo = true)
    override fun load(disbursableId: String) = config.scope.later {
        logger.info("loading a disbursable(uid=$disbursableId)")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("update a disbursement"),
            data = disbursableId
        )
        load(rb).await().also { logger.info("Success") }
    }

    override fun delete(ids: Array<out String>): Later<List<D>> = config.scope.later {
        logger.info("Deleting disbursables(${ids.joinToString(",")}")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("delete multiple disbursables"),
            data = ids
        )
        delete(rb).await().also { logger.info("Success") }
    }

    override fun all(params: DisbursableRawFilter?): Later<List<DS>> = config.scope.later {
        logger.info("Loading all disbursables")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load all disbursables"),
            data = params.toValidateParams()
        )
        all(rb).await().also { logger.info("Success") }
    }

    override fun createDisbursement(params: DisbursableDisbursementParams): Later<Disbursement> = config.scope.later {
        logger.info("Creating a disbursement")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("create a disbursement"),
            data = params.toValidatedParams()
        )
        createDisbursement(rb).await().also { logger.info("Disbursement created successfully") }
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