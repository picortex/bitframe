package pimonitor.core.businesses

import bitframe.core.*
import bitframe.core.users.RegisterUserUseCase
import bitframe.core.users.RegisterUserUseCaseImpl
import kotlinx.collections.interoperable.toInteroperableList
import later.await
import later.later
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.toRegisterUserParams
import pimonitor.core.contacts.ContactPersonSpaceInfo

open class BusinessesDaodService(
    open val config: DaodServiceConfig
) : BusinessesServiceCore, RegisterUserUseCase by RegisterUserUseCaseImpl(config) {

    private val businessDao by lazy { config.daoFactory.get<MonitoredBusinessBasicInfo>() }
    private val spacesDao by lazy { config.daoFactory.get<Space>() }
    private val contactPersonSpaceInfoDao by lazy {
        config.daoFactory.get<ContactPersonSpaceInfo>()
    }

    private val logger
        get() = config.logger.with(
            "source" to this::class.simpleName
        )

    override fun create(rb: RequestBody.Authorized<CreateMonitoredBusinessParams>) = config.scope.later {
        val res1 = register(rb.data.toRegisterUserParams()).await()
        val task2_1 = businessDao.create(
            MonitoredBusinessBasicInfo(
                spaceId = res1.spaces.first().uid,
                owningSpaceId = rb.session.space.uid
            )
        )
        val task2_2 = contactPersonSpaceInfoDao.create(
            ContactPersonSpaceInfo(
                userId = res1.user.uid,
                spaceId = res1.spaces.first().uid,
                owningSpaceId = rb.session.space.uid,
                position = ""
            )
        )
        task2_1.await(); task2_2.await();
        rb.data
    }

    override fun all(rb: RequestBody.Authorized<BusinessFilter>) = config.scope.later {
        val condition = MonitoredBusinessBasicInfo::owningSpaceId isEqualTo rb.session.space.uid
        businessDao.all(condition).await().toTypedArray().map {
            summaryOf(it)
        }.toInteroperableList()
    }

    private suspend fun summaryOf(business: MonitoredBusinessBasicInfo): MonitoredBusinessSummary {
        val space = spacesDao.load(business.spaceId).await()
        return if (business.dashboard == DASHBOARD.NONE) {
            MonitoredBusinessSummary.UnConnectedDashboard(
                uid = business.uid,
                name = space.name,
                interventions = "0 of 0"
            )
        } else {
            TODO()
        }
    }
}