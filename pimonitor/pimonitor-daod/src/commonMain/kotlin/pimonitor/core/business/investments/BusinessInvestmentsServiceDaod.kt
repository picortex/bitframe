package pimonitor.core.business.investments

import bitframe.core.*
import datetime.SimpleDateTime
import kotlinx.collections.interoperable.toInteroperableList
import later.await
import later.later
import pimonitor.core.business.investments.params.CreateInvestmentDisbursementParams
import pimonitor.core.business.investments.params.CreateInvestmentsParams
import pimonitor.core.business.investments.params.toInvestment
import pimonitor.core.business.investments.params.toValidatedCreateInvestmentsParams
import pimonitor.core.business.utils.disbursements.Disbursement
import pimonitor.core.business.utils.disbursements.toDisbursement

open class BusinessInvestmentsServiceDaod(
    val config: ServiceConfigDaod
) : BusinessInvestmentsServiceCore {

    private val factory get() = config.daoFactory
    private val investmentsDao by lazy { factory.get<Investment>() }

    override fun capture(rb: RequestBody.Authorized<CreateInvestmentsParams>) = config.scope.later {
        val params = rb.data.toValidatedCreateInvestmentsParams()
        val history = InvestmentHistory.Created(
            on = SimpleDateTime.now,
            by = rb.session.user.ref()
        )
        investmentsDao.create(params.toInvestment(history)).await()
    }

    override fun all(rb: RequestBody.Authorized<String>) = config.scope.later {
        investmentsDao.all(Investment::businessId isEqualTo rb.data).await()
    }

    override fun disburse(rb: RequestBody.Authorized<CreateInvestmentDisbursementParams>) = config.scope.later {
        val investment = investmentsDao.load(rb.data.investmentId).await()
        val disbursement = rb.data.toDisbursement(rb.session)
        val input = investment.copy(
            disbursements = (investment.disbursements + disbursement).toInteroperableList()
        )
        investmentsDao.update(input).await()
        disbursement
    }
}