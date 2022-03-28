package pimonitor.core.business.investments

import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import bitframe.core.get
import bitframe.core.isEqualTo
import datetime.SimpleDateTime
import kotlinx.collections.interoperable.List
import later.Later
import later.await
import later.later
import pimonitor.core.business.investments.*
import pimonitor.core.invites.Invite

open class BusinessInvestmentsServiceDaod(
    val config: ServiceConfigDaod
) : BusinessInvestmentsServiceCore {

    private val factory get() = config.daoFactory
    private val investmentsDao by lazy { factory.get<Investment>() }

    override fun capture(rb: RequestBody.Authorized<CaptureInvestmentsParams>) = config.scope.later {
        val params = rb.data.toValidatedCaptureInvestmentsParams()
        val history = InvestmentHistory.Created(
            on = SimpleDateTime.now,
            by = rb.session.user.ref()
        )
        investmentsDao.create(params.toInvestment(history)).await()
    }

    override fun all(rb: RequestBody.Authorized<String>) = config.scope.later {
        investmentsDao.all(Investment::businessId isEqualTo rb.data).await()
    }
}