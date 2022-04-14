package pimonitor.core.investments

import bitframe.core.*
import datetime.Date
import kash.Currency
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.coroutines.async
import kotlinx.datetime.TimeZone
import later.Later
import later.await
import later.later
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.investments.filters.InvestmentFilter
import pimonitor.core.investments.params.InvestmentDisbursementParams
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.investments.params.InvestmentsParsedParams
import pimonitor.core.investments.params.toValidatedParams
import pimonitor.core.utils.disbursements.params.toParsedParams

open class InvestmentsServiceDaod(
    val config: ServiceConfigDaod
) : InvestmentsServiceCore {

    private val factory get() = config.daoFactory
    private val monitoredBusinessesDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val investmentsDao by lazy { factory.get<Investment>() }

    private val currency: Currency = Currency.ZAR
    private val timezone: TimeZone = TimeZone.UTC

    override fun create(rb: RequestBody.Authorized<InvestmentParams>) = config.scope.later {
        val params = rb.data.toValidatedParams().toParsedParams(currency)
        val history = InvestmentHistory.Created(
            on = Date.today(timezone),
            by = rb.session.user.ref()
        )
        investmentsDao.create(params.toInvestment(spaceId = rb.session.space.uid, history)).await()
    }

    override fun update(rb: RequestBody.Authorized<Identified<InvestmentParams>>): Later<Investment> = config.scope.later {
        val params = rb.data.map { it.toParsedParams(currency) }
        val investment = investmentsDao.load(uid = params.uid).await()
        investmentsDao.update(investment.merge(params.body, rb.session.user.ref())).await()
    }

    override fun all(rb: RequestBody.Authorized<InvestmentFilter>) = config.scope.later {
        val params = rb.data
        val condition = when (val businessId = params.businessId) {
            is String -> Investment::businessId isEqualTo businessId
            else -> Investment::owningSpaceId isEqualTo rb.session.space.uid
        }
        investmentsDao.all(condition).await().toTypedArray().map { it.toSummary() }.toInteroperableList()
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

    override fun delete(rb: RequestBody.Authorized<Array<out String>>) = config.scope.later {
        rb.data.map { uid ->
            async {
                val investment = investmentsDao.load(uid).await()
                investmentsDao.update(investment.copy(deleted = true)).await()
            }
        }.map { it.await() }.toInteroperableList()
    }

    private fun Investment.merge(params: InvestmentsParsedParams, by: UserRef) = copy(
        businessId = params.businessId,
        name = params.name,
        type = params.type,
        source = params.source,
        amount = params.amount,
        date = params.date,
        details = params.details,
        history = (history + InvestmentHistory.Updated(Date.today(timezone), by)).toInteroperableList()
    )

    private suspend fun Investment.toSummary(): InvestmentSummary = InvestmentSummary(
        businessId = businessId,
        businessName = monitoredBusinessesDao.load(uid = businessId).await().name,
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