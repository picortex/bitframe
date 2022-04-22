package pimonitor.core.interventions

import bitframe.core.Identified
import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import bitframe.core.get
import datetime.Date
import kash.Currency
import kotlinx.collections.interoperable.List
import kotlinx.datetime.TimeZone
import later.Later
import later.await
import later.later
import logging.Logger
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.interventions.params.InterventionParams
import pimonitor.core.interventions.params.toParsedParams
import pimonitor.core.interventions.params.toValidatedParams
import pimonitor.core.utils.disbursables.DisbursableServiceDaod
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams

open class InterventionsServiceDaod(
    override val config: ServiceConfigDaod,
    private val currency: Currency = Currency.ZAR,
    private val timezone: TimeZone = TimeZone.UTC
) : DisbursableServiceDaod<Intervention, InterventionSummary>(config, currency, timezone), InterventionsServiceCore {
    private val factory get() = config.daoFactory
    private val monitoredBusinessDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    override val disbursableDao by lazy { factory.get<Intervention>() }

    override fun create(rb: RequestBody.Authorized<InterventionParams>) = config.scope.later {
        val params = rb.data.toValidatedParams().toParsedParams(currency)
        val history = InterventionHistory.Created(
            on = Date.today(),
            by = rb.session.user.ref()
        )
        disbursableDao.create(params.toIntervention(history, rb.session.space.uid)).await()
    }

    override fun update(rb: RequestBody.Authorized<Identified<InterventionParams>>): Later<Intervention> {
        TODO("Not yet implemented")
    }

    override fun createGoal(rb: RequestBody.Authorized<DisbursableDisbursementParams>): Later<Goal> {
        TODO("Not yet implemented")
    }

    override fun deleteGoal(rb: RequestBody.Authorized<Identified<Array<String>>>): Later<List<Goal>> {
        TODO("Not yet implemented")
    }

    override fun updateGoal(rb: RequestBody.Authorized<Identified<DisbursableDisbursementParams>>): Later<Goal> {
        TODO("Not yet implemented")
    }

    override suspend fun Intervention.toSummary(): InterventionSummary = InterventionSummary(
        uid = uid,
        owningSpaceId = owningSpaceId,
        businessId = businessId,
        businessName = monitoredBusinessDao.load(businessId).await().name,
        name = name,
        amount = amount,
        date = date,
        deadline = deadline,
        recommendations = recommendations,
        goals = goals,
        history = history,
        disbursements = disbursements,
        deleted = deleted
    )
}