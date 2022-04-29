package pimonitor.core.utils.disbursables.disbursements

import bitframe.core.Dao
import bitframe.core.Identified
import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.datetime.TimeZone
import later.await
import later.later
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import pimonitor.core.utils.disbursables.disbursements.params.toParsedParams

class DisbursementsServiceDaod(
    private val config: ServiceConfigDaod,
    private val disbursableDao: Dao<Disbursable>,
    private val timezone: TimeZone
) : DisbursementsServiceCore {

    private val Disbursable.currency get() = amount.currency

    override fun create(params: RequestBody.Authorized<DisbursableDisbursementParams>) = config.scope.later {
        val disbursable = disbursableDao.load(params.data.disbursableId).await()
        val disbursements = disbursable.disbursements
        val disbursement = params.data.toParsedParams(disbursable.currency).toDisbursement(params.session, timezone, disbursements.size)
        disbursableDao.update(disbursable.copy((disbursements + disbursement).toInteroperableList())).await()
        disbursement
    }

    override fun update(params: RequestBody.Authorized<Identified<DisbursableDisbursementParams>>) = config.scope.later {
        val disbursable = disbursableDao.load(params.data.uid).await()
        val disbursements = disbursable.disbursements.map {
            if (it.uid != params.data.uid) return@map it
            val param = params.data.body.toParsedParams(disbursable.currency)
            it.copy(amount = param.amount, date = param.date)
        }.toInteroperableList()
        disbursableDao.update(disbursable.copy(disbursements)).await()
        disbursements.first { it.uid in params.data.uid }
    }

    override fun delete(params: RequestBody.Authorized<Identified<Array<out String>>>) = config.scope.later {
        val disbursable = disbursableDao.load(params.data.uid).await()
        val ids = params.data.body
        val disbursements = disbursable.disbursements.map {
            if (it.uid in ids) it.copy(deleted = true) else it
        }.toInteroperableList()
        disbursableDao.update(disbursable.copy(disbursements)).await()
        disbursements.filter { it.uid in ids }.toInteroperableList()
    }
}