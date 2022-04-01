package pimonitor.core.business.interventions

import bitframe.core.*
import datetime.SimpleDateTime
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.await
import later.later
import pimonitor.core.business.interventions.params.CreateInterventionDisbursementParams
import pimonitor.core.business.investments.params.CreateInvestmentDisbursementParams
import pimonitor.core.business.investments.params.CreateInvestmentsParams
import pimonitor.core.business.investments.params.toInvestment
import pimonitor.core.business.investments.params.toValidatedCreateInvestmentsParams
import pimonitor.core.business.utils.disbursements.Disbursement
import pimonitor.core.business.utils.disbursements.toDisbursement

open class BusinessInterventionsServiceDaod(
    val config: ServiceConfigDaod
) : BusinessInterventionsServiceCore {

    private val factory get() = config.daoFactory
    private val interventionsDao by lazy { factory.get<Intervention>() }

    override fun create(rb: RequestBody.Authorized<Any>): Later<Intervention> {
        TODO("Not yet implemented")
    }
//    override fun create(rb: RequestBody.Authorized<CreateInvestmentsParams>) = config.scope.later {
//        val params = rb.data.toValidatedCreateInvestmentsParams()
//        val history = InterventionHistory.Created(
//            on = SimpleDateTime.now,
//            by = rb.session.user.ref()
//        )
//        interventionsDao.create(params.toInvestment(history)).await()
//    }

    override fun addGoal(rb: RequestBody.Authorized<Any>): Later<Any> {
        TODO("Not yet implemented")
    }

    override fun all(rb: RequestBody.Authorized<String>) = config.scope.later {
        interventionsDao.all(Intervention::businessId isEqualTo rb.data).await()
    }

    override fun disburse(rb: RequestBody.Authorized<CreateInterventionDisbursementParams>) = config.scope.later {
        val intervention = interventionsDao.load(rb.data.interventionId).await()
        val disbursement = rb.data.toDisbursement(rb.session)
        val input = intervention.copy(
            disbursements = (intervention.disbursements + disbursement).toInteroperableList()
        )
        interventionsDao.update(input).await()
        disbursement
    }
}