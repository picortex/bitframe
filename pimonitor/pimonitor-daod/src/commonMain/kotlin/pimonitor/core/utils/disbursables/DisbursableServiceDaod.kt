package pimonitor.core.utils.disbursables

import bitframe.core.Identified
import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import bitframe.core.map
import kash.Currency
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.datetime.TimeZone
import later.Later
import later.await
import later.later
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import pimonitor.core.utils.disbursables.disbursements.params.toParsedParams

abstract class DisbursableServiceDaod<out D : Disbursable>(
    private val config: ServiceConfigDaod,
    private val currency: Currency,
    private val timezone: TimeZone
) : DisbursableServiceCore<D> {

    abstract override fun load(rb: RequestBody.Authorized<String>): Later<D>

    protected abstract fun update(disbursable: @UnsafeVariance D): Later<D>

    override fun createDisbursement(rb: RequestBody.Authorized<DisbursableDisbursementParams>): Later<Disbursement> = config.scope.later {
        val disbursable = load(rb.map { it.disbursableId }).await()
        val disbursements = disbursable.disbursements
        val disbursement = rb.data.toParsedParams(currency).toDisbursement(rb.session, timezone, disbursements.size)
        update(disbursable.copy((disbursements + disbursement).toInteroperableList()) as D).await()
        disbursement
    }

    override fun deleteDisbursement(rb: RequestBody.Authorized<Identified<Array<String>>>): Later<List<Disbursement>> = config.scope.later {
        val disbursable = load(rb.map { it.uid }).await()
        val ids = rb.data.body
        val disbursements = disbursable.disbursements.map {
            if (it.uid in ids) it.copy(deleted = true) else it
        }.toInteroperableList()
        update(disbursable.copy(disbursements) as D).await()
        disbursements.filter { it.uid in ids }.toInteroperableList()
    }

    override fun updateDisbursement(rb: RequestBody.Authorized<Identified<DisbursableDisbursementParams>>): Later<Disbursement> = config.scope.later {
        val disbursable = load(rb.map { it.body.disbursableId }).await()
        val disbursements = disbursable.disbursements.map {
            if (it.uid != rb.data.uid) return@map it
            val params = rb.data.body.toParsedParams(currency)
            it.copy(amount = params.amount, date = params.date)
        }.toInteroperableList()
        update(disbursable.copy(disbursements) as D).await()
        disbursements.first { it.uid in rb.data.uid }
    }
}