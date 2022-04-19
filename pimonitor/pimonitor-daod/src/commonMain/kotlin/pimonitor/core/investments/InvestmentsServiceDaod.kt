package pimonitor.core.investments

import bitframe.core.*
import datetime.Date
import kash.Currency
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.datetime.TimeZone
import later.Later
import later.await
import later.later
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.investments.params.InvestmentsParsedParams
import pimonitor.core.investments.params.toValidatedParams
import pimonitor.core.utils.disbursables.DisbursableServiceDaod

open class InvestmentsServiceDaod(
    override val config: ServiceConfigDaod,
    private val currency: Currency = Currency.ZAR,
    private val timezone: TimeZone = TimeZone.UTC
) : DisbursableServiceDaod<Investment, InvestmentSummary>(config, currency, timezone), InvestmentsServiceCore {

    private val factory get() = config.daoFactory
    private val monitoredBusinessesDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    override val disbursableDao by lazy { factory.get<Investment>() }

    override fun create(rb: RequestBody.Authorized<InvestmentParams>) = config.scope.later {
        val params = rb.data.toValidatedParams().toParsedParams(currency)
        val history = InvestmentHistory.Created(
            on = Date.today(timezone),
            by = rb.session.user.ref()
        )
        disbursableDao.create(params.toInvestment(spaceId = rb.session.space.uid, history)).await()
    }

    override fun update(rb: RequestBody.Authorized<Identified<InvestmentParams>>): Later<Investment> = config.scope.later {
        val params = rb.data.map { it.toParsedParams(currency) }
        val investment = disbursableDao.load(uid = params.uid).await()
        disbursableDao.update(investment.merge(params.body, rb.session.user.ref())).await()
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

    override suspend fun Investment.toSummary(): InvestmentSummary = InvestmentSummary(
        uid = uid,
        owningSpaceId = owningSpaceId,
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
        deleted = deleted,
    )
}