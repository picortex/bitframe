package pimonitor.core.investments

import bitframe.core.*
import datetime.Date
import datetime.SimpleDateTime
import kash.Currency
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.datetime.TimeZone
import later.await
import later.later
import pimonitor.core.business.utils.disbursements.toParsedParams
import pimonitor.core.investments.params.InvestmentDisbursementParams
import pimonitor.core.investments.params.InvestmentsParams
import pimonitor.core.investments.params.toValidatedParams

open class BusinessInvestmentsServiceDaod(
    val config: ServiceConfigDaod
) : BusinessInvestmentsServiceCore {

    private val factory get() = config.daoFactory
    private val investmentsDao by lazy { factory.get<Investment>() }

    private val currency: Currency = Currency.ZAR
    private val timezone: TimeZone = TimeZone.UTC

    override fun capture(rb: RequestBody.Authorized<InvestmentsParams>) = config.scope.later {
        val params = rb.data.toValidatedParams().toParsedParams(currency)
        val history = InvestmentHistory.Created(
            on = Date.today(timezone),
            by = rb.session.user.ref()
        )
        investmentsDao.create(params.toInvestment(rb.session.space.uid, history)).await()
    }

    override fun all(rb: RequestBody.Authorized<String>) = config.scope.later {
        investmentsDao.all(Investment::businessId isEqualTo rb.data).await()
    }

    override fun disburse(rb: RequestBody.Authorized<InvestmentDisbursementParams>) = config.scope.later {
        val investment = investmentsDao.load(rb.data.investmentId).await()
        val disbursement = rb.data.toParsedParams(currency).toDisbursement(rb.session, timezone)
        val input = investment.copy(
            disbursements = (investment.disbursements + disbursement).toInteroperableList()
        )
        investmentsDao.update(input).await()
        disbursement
    }
}