package pimonitor.core.investments

import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import bitframe.core.get
import bitframe.core.isEqualTo
import datetime.Date
import kash.Currency
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.datetime.TimeZone
import later.await
import later.later
import pimonitor.core.investments.params.InvestmentDisbursementParams
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.investments.params.toValidatedParams
import pimonitor.core.utils.disbursables.disbursements.params.toParsedParams

open class BusinessInvestmentsServiceDaod(
    val config: ServiceConfigDaod
) : BusinessInvestmentsServiceCore {

    private val factory get() = config.daoFactory
    private val investmentsDao by lazy { factory.get<Investment>() }

    private val currency: Currency = Currency.ZAR
    private val timezone: TimeZone = TimeZone.UTC

    override fun capture(rb: RequestBody.Authorized<InvestmentParams>) = config.scope.later {
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