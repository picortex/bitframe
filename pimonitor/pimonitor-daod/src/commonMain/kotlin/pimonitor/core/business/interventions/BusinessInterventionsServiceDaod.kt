package pimonitor.core.business.interventions

import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import bitframe.core.get
import bitframe.core.isEqualTo
import datetime.Date
import kash.Currency
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.datetime.TimeZone
import later.Later
import later.await
import later.later
import pimonitor.core.business.interventions.params.*
import pimonitor.core.utils.disbursables.disbursements.params.toParsedParams

open class BusinessInterventionsServiceDaod(
    val config: ServiceConfigDaod
) : BusinessInterventionsServiceCore {

    private val factory get() = config.daoFactory
    private val interventionsDao by lazy { factory.get<Intervention>() }

    private val currency = Currency.ZAR
    private val timezone = TimeZone.UTC

    override fun create(rb: RequestBody.Authorized<InterventionParams>) = config.scope.later {
        val params = rb.data.toValidatedParams().toParsedParams(currency)
        val history = InterventionHistory.Created(
            on = Date.today(),
            by = rb.session.user.ref()
        )
        interventionsDao.create(params.toIntervention(history)).await()
    }

    override fun addGoal(rb: RequestBody.Authorized<CreateGoalParams>): Later<Any> {
        TODO("Not yet implemented")
    }

    override fun all(rb: RequestBody.Authorized<String>) = config.scope.later {
        interventionsDao.all(Intervention::businessId isEqualTo rb.data).await()
    }

    override fun disburse(rb: RequestBody.Authorized<CreateInterventionDisbursementParams>) = config.scope.later {
        val intervention = interventionsDao.load(rb.data.interventionId).await()
        val disbursement = rb.data.toParsedParams(currency).toDisbursement(rb.session, timezone)
        val input = intervention.copy(
            disbursements = (intervention.disbursements + disbursement).toInteroperableList()
        )
        interventionsDao.update(input).await()
        disbursement
    }
}