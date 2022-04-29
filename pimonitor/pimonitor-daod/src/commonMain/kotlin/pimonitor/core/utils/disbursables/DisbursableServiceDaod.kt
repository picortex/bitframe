package pimonitor.core.utils.disbursables

import bitframe.core.*
import kash.Currency
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.coroutines.async
import kotlinx.datetime.TimeZone
import later.Later
import later.await
import later.later
import logging.Logger
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.DisbursementsServiceCore
import pimonitor.core.utils.disbursables.disbursements.DisbursementsServiceDaod
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import pimonitor.core.utils.disbursables.disbursements.params.toParsedParams
import pimonitor.core.utils.disbursables.filters.DisbursableFilter

abstract class DisbursableServiceDaod<out D : Disbursable, out DS : DisbursableSummary>(
    open val config: ServiceConfigDaod,
    private val currency: Currency,
    private val timezone: TimeZone
) : DisbursableServiceCore<D, DS> {

    protected val logger: Logger
        get() = config.logger.with(
            "source" to this::class.simpleName
        )

    abstract val disbursableDao: Dao<D>

    override val disbursements: DisbursementsServiceCore by lazy {
        DisbursementsServiceDaod(config, disbursableDao, timezone)
    }

    override fun load(rb: RequestBody.Authorized<String>) = config.scope.later {
        val log by config.logger(rb, logger)
        log.info("Loading disbursable")
        disbursableDao.load(rb.data).await().toSummary().also { log.info("Successfully loaded disbursable: ${it::class.simpleName}") }
    }

    override fun all(rb: RequestBody.Authorized<DisbursableFilter>): Later<List<DS>> = config.scope.later {
        val log by config.logger(rb, logger)
        log.info("Loading all disbursables")
        val params = rb.data
        val condition = when (val businessId = params.businessId) {
            is String -> Disbursable::businessId isEqualTo businessId
            else -> Disbursable::owningSpaceId isEqualTo rb.session.space.uid
        }
        disbursableDao.all(condition).await().toSet().map { it.toSummary() }.toInteroperableList().also {
            log.info("Success")
        }
    }

    override fun delete(rb: RequestBody.Authorized<Array<out String>>) = config.scope.later {
        rb.data.map { uid ->
            async {
                val disbursable = disbursableDao.load(uid).await()
                disbursableDao.update(disbursable.copySavable(uid = uid, deleted = true) as D).await()
            }
        }.map { it.await() }.toInteroperableList()
    }

    abstract suspend fun @UnsafeVariance D.toSummary(): DS
}