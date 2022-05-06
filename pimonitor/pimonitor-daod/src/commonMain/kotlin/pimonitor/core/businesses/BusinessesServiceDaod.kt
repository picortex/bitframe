package pimonitor.core.businesses

import bitframe.core.*
import bitframe.core.users.RegisterUserUseCase
import bitframe.core.users.RegisterUserUseCaseImpl
import kotlinx.collections.interoperable.mutableListOf
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.await
import later.later
import pimonitor.core.business.info.params.BusinessInfoParams
import pimonitor.core.business.info.params.toValidatedParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessResult
import pimonitor.core.businesses.params.toRegisterUserParams
import pimonitor.core.businesses.params.toValidatedCreateBusinessParams
import pimonitor.core.contacts.ContactPersonBusinessInfo
import pimonitor.core.spaces.SPACE_TYPE
import pimonitor.core.users.USER_TYPE

open class BusinessesServiceDaod(
    open val config: ServiceConfigDaod
) : BusinessesServiceCore, RegisterUserUseCase by RegisterUserUseCaseImpl(config) {

    private val factory get() = config.daoFactory
    private val monitoredBusinessesDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val spacesDao by lazy { factory.get<Space>() }
    private val userSpaceInfoDao by lazy { factory.get<UserSpaceInfo>() }
    private val userContactsDao by lazy { CompoundDao(factory.get<UserEmail>(), factory.get<UserPhone>()) }
    private val contactPersonBusinessInfoDao by lazy { factory.get<ContactPersonBusinessInfo>() }

    private val summary by lazy { BusinessSummaryUseCase(config) }

    override fun create(rb: RequestBody.Authorized<CreateMonitoredBusinessParams>) = config.scope.later {
        val params = rb.data.toValidatedCreateBusinessParams()
        val registered = userContactsDao.all(
            condition = UserContact::value isEqualTo params.contactEmail
        ).await().firstOrNull()

        val userId = if (registered == null) {
            val res1 = register(params.toRegisterUserParams()).await()
            res1.user.uid
        } else {
            val space = spacesDao.create(Space(name = params.businessName, type = SPACE_TYPE.MONITORED)).await()
            val usi = UserSpaceInfo(
                userId = registered.userId, spaceId = space.uid, type = USER_TYPE.CONTACT
            )
            userSpaceInfoDao.create(usi).await()
            registered.userId
        }

        val business = monitoredBusinessesDao.create(
            MonitoredBusinessBasicInfo(
                name = params.businessName, owningSpaceId = rb.session.space.uid
            )
        ).await()
        val cpbi = contactPersonBusinessInfoDao.create(
            ContactPersonBusinessInfo(
                userId = userId, businessId = business.uid, position = ""
            )
        ).await()
        CreateMonitoredBusinessResult(
            params = params, business = business, contact = cpbi, summary = summary(business)
        )
    }

    override fun load(rb: RequestBody.Authorized<String>): Later<MonitoredBusinessBasicInfo> = config.scope.later {
        monitoredBusinessesDao.load(uid = rb.data).await()
    }

    fun MonitoredBusinessBasicInfo.updated(params: BusinessInfoParams) = copy(
        name = params.name,
        industry = params.industry,
        address = params.address,
        phone = params.phone,
        email = params.email,
        website = params.website,
        about = params.about,
    )

    override fun update(rb: RequestBody.Authorized<BusinessInfoParams>): Later<MonitoredBusinessBasicInfo> = config.scope.later {
        val params = rb.data.toValidatedParams()
        val business = monitoredBusinessesDao.load(params.businessId).await()
        monitoredBusinessesDao.update(business.updated(params)).await()
    }

    override fun all(rb: RequestBody.Authorized<BusinessFilter>) = config.scope.later {
        val condition = MonitoredBusinessBasicInfo::owningSpaceId isEqualTo rb.session.space.uid
        monitoredBusinessesDao.all(condition).await().filter { !it.deleted }.toTypedArray().map {
            summary(it)
        }.toInteroperableList()
    }

    override fun delete(rb: RequestBody.Authorized<Array<out String>>) = config.scope.later {
        val list = mutableListOf<MonitoredBusinessBasicInfo>()
        for (business in rb.data) list.add(monitoredBusinessesDao.delete(business).await())
        list
    }
}