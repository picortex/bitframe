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
import pimonitor.core.investments.Investment
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import pimonitor.core.utils.disbursables.disbursements.params.toParsedParams
import pimonitor.core.utils.disbursables.filters.DisbursableFilter

abstract class DisbursableServiceDaod<out D : Disbursable, out DS : DisbursableSummary>(
    private val config: ServiceConfigDaod,
    private val currency: Currency,
    private val timezone: TimeZone
) : DisbursableServiceCore<D, DS> {

    abstract val disbursableDao: Dao<D>

    override fun load(rb: RequestBody.Authorized<String>): Later<D> = disbursableDao.load(rb.data)

    override fun createDisbursement(rb: RequestBody.Authorized<DisbursableDisbursementParams>): Later<Disbursement> = config.scope.later {
        val disbursable = load(rb.map { it.disbursableId }).await()
        val disbursements = disbursable.disbursements
        val disbursement = rb.data.toParsedParams(currency).toDisbursement(rb.session, timezone, disbursements.size)
        disbursableDao.update(disbursable.copy((disbursements + disbursement).toInteroperableList()) as D).await()
        disbursement
    }

    override fun deleteDisbursement(rb: RequestBody.Authorized<Identified<Array<String>>>): Later<List<Disbursement>> = config.scope.later {
        val disbursable = load(rb.map { it.uid }).await()
        val ids = rb.data.body
        val disbursements = disbursable.disbursements.map {
            if (it.uid in ids) it.copy(deleted = true) else it
        }.toInteroperableList()
        disbursableDao.update(disbursable.copy(disbursements) as D).await()
        disbursements.filter { it.uid in ids }.toInteroperableList()
    }

    override fun updateDisbursement(rb: RequestBody.Authorized<Identified<DisbursableDisbursementParams>>): Later<Disbursement> = config.scope.later {
        val disbursable = load(rb.map { it.body.disbursableId }).await()
        val disbursements = disbursable.disbursements.map {
            if (it.uid != rb.data.uid) return@map it
            val params = rb.data.body.toParsedParams(currency)
            it.copy(amount = params.amount, date = params.date)
        }.toInteroperableList()
        disbursableDao.update(disbursable.copy(disbursements) as D).await()
        disbursements.first { it.uid in rb.data.uid }
    }

    override fun all(rb: RequestBody.Authorized<DisbursableFilter>): Later<List<DS>> = config.scope.later {
        val params = rb.data
        val condition = when (val businessId = params.businessId) {
            is String -> Disbursable::businessId isEqualTo businessId
            else -> Disbursable::owningSpaceId isEqualTo rb.session.space.uid
        }
        disbursableDao.all(condition).await().toSet().map { it.toSummary() }.toInteroperableList()
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