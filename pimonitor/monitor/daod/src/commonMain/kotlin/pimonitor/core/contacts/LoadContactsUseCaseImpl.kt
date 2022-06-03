package pimonitor.core.contacts

import bitframe.core.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.await
import later.later
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.toMonitoredBusinessRef
import pimonitor.core.search.SearchResult.ContactPersonSummary

class LoadContactsUseCaseImpl(
    private val config: ServiceConfigDaod
) : LoadContactsUseCase {
    private val factory get() = config.daoFactory
    private val businessDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val usersDao by lazy { factory.get<User>() }
    private val contactsDao by lazy { CompoundDao(factory.get<UserPhone>(), factory.get<UserEmail>()) }
    private val userSpaceInfoDao by lazy { factory.get<UserSpaceInfo>() }
    private val spacesDao by lazy { factory.get<Space>() }
    private val contactBusinessInfoDao by lazy { factory.get<ContactPersonBusinessInfo>() }

    override fun all(rb: RequestBody.Authorized<ContactsFilter>): Later<List<ContactPersonSummary>> = config.scope.later {
        val session = rb.session
        businessDao.all(
            condition = MonitoredBusinessBasicInfo::owningSpaceId isEqualTo session.space.uid
        ).await().flatMap { info ->
            contactBusinessInfoDao.all(ContactPersonBusinessInfo::businessId isEqualTo info.uid).await().toTypedArray().map {
                it.toContactPersonSummary(info)
            }
        }.toInteroperableList()
    }

    private suspend fun ContactPersonBusinessInfo.toContactPersonSummary(
        business: MonitoredBusinessBasicInfo
    ): ContactPersonSummary {
        val user = usersDao.load(userId).await()
        val contacts = contactsDao.all(UserContact::userId isEqualTo user.uid).await()
        val info = contactBusinessInfoDao.all(ContactPersonBusinessInfo::userId isEqualTo userId).await().first()
        return ContactPersonSummary(
            userId = userId,
            name = user.name,
            email = contacts.filterIsInstance<UserEmail>().firstOrNull()?.value ?: "",
            phone = contacts.filterIsInstance<UserPhone>().firstOrNull()?.value ?: "",
            position = info.position,
            business = business.toMonitoredBusinessRef()
        )
    }
}