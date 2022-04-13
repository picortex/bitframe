package pimonitor.core.investments

import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import bitframe.core.get
import bitframe.core.isEqualTo
import datetime.SimpleDateTime
import kash.Currency
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.coroutines.async
import kotlinx.datetime.TimeZone
import later.Later
import later.await
import later.later
import pimonitor.core.business.utils.disbursements.toParsedParams
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.investments.params.CreateInvestmentDisbursementParams
import pimonitor.core.investments.params.CreateInvestmentsParams
import pimonitor.core.investments.params.toValidatedParams

open class InvestmentsServiceDaod(
    val config: ServiceConfigDaod
) : InvestmentsServiceCore {

    private val factory get() = config.daoFactory
    private val monitoredBusinessesDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val investmentsDao by lazy { factory.get<Investment>() }

    private val currency: Currency = Currency.ZAR
    private val timezone: TimeZone = TimeZone.UTC

    override fun capture(rb: RequestBody.Authorized<CreateInvestmentsParams>) = config.scope.later {
        val params = rb.data.toValidatedParams().toParsedParams(currency)
        val history = InvestmentHistory.Created(
            on = SimpleDateTime.now,
            by = rb.session.user.ref()
        )
        investmentsDao.create(params.toInvestment(spaceId = rb.session.space.uid, history)).await().toSummary()
    }

    override fun all(rb: RequestBody.Authorized<InvestmentFilter>) = config.scope.later {
        val params = rb.data
        val condition = when (val businessId = params.businessId) {
            is String -> Investment::businessId isEqualTo businessId
            else -> Investment::owningSpaceId isEqualTo rb.session.space.uid
        }
        investmentsDao.all(condition).await().toTypedArray().map { it.toSummary() }.toInteroperableList()
    }

    override fun disburse(rb: RequestBody.Authorized<CreateInvestmentDisbursementParams>) = config.scope.later {
        val investment = investmentsDao.load(rb.data.investmentId).await()
        val disbursement = rb.data.toParsedParams(currency).toDisbursement(rb.session, timezone)
        val input = investment.copy(
            disbursements = (investment.disbursements + disbursement).toInteroperableList()
        )
        investmentsDao.update(input).await()
        disbursement
    }

    override fun delete(rb: RequestBody.Authorized<Array<out String>>) = config.scope.later {
        rb.data.map { uid ->
            async {
                val investment = investmentsDao.load(uid).await()
                investmentsDao.update(investment.copy(deleted = true)).await()
            }
        }.map { it.await() }.toInteroperableList()
    }

    private suspend fun Investment.toSummary(): InvestmentSummary = InvestmentSummary(
        businessId = businessId,
        businessName = monitoredBusinessesDao.load(uid = uid).await().name,
        name = name,
        type = type,
        source = source,
        amount = amount,
        date = date,
        details = details,
        history = history,
        disbursements = disbursements,
        createdBy = createdBy,
        totalDisbursed = totalDisbursed,
        disbursementProgressInPercentage = disbursementProgressInPercentage,
    )
}