package pimonitor.core.contacts

import bitframe.core.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.await
import later.later
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.toMonitoredBusinessRef
import pimonitor.core.search.SearchResult
import pimonitor.core.search.SearchResult.ContactPersonSummary

class LoadContactsUseCaseImpl(
    private val config: DaodServiceConfig
) : LoadContactsUseCase {
    val businessDao by lazy { config.daoFactory.get<MonitoredBusinessBasicInfo>() }
    val usersDao by lazy { config.daoFactory.get<User>() }
    val contactsDao by lazy {
        CompoundDao(
            config.daoFactory.get<UserPhone>(),
            config.daoFactory.get<UserEmail>()
        )
    }
    val userSpaceInfoDao by lazy { config.daoFactory.get<UserSpaceInfo>() }
    val spacesDao by lazy { config.daoFactory.get<Space>() }
    val contactSpaceInfoDao by lazy { config.daoFactory.get<ContactPersonSpaceInfo>() }

    override fun all(rb: RequestBody.Authorized<ContactsFilter>): Later<List<ContactPersonSummary>> = config.scope.later {
        val session = rb.session
        val condition = MonitoredBusinessBasicInfo::owningSpaceId isEqualTo session.space.uid
        businessDao.all(condition).await().flatMap { info ->
            userSpaceInfoDao.all(UserSpaceInfo::spaceId isEqualTo info.spaceId).await().toTypedArray().map {
                it.toContactPersonSummary(info)
            }
        }.toInteroperableList()
    }

    private suspend fun UserSpaceInfo.toContactPersonSummary(
        business: MonitoredBusinessBasicInfo
    ): ContactPersonSummary {
        val user = usersDao.load(userId).await()
        val contacts = contactsDao.all(UserContact::userId isEqualTo user.uid).await()
        val info = contactSpaceInfoDao.all(ContactPersonSpaceInfo::userId isEqualTo userId).await().first()
        val space = spacesDao.load(business.spaceId).await()
        return ContactPersonSummary(
            userId = userId,
            name = user.name,
            email = contacts.filterIsInstance<UserEmail>().firstOrNull()?.value ?: "",
            phone = contacts.filterIsInstance<UserPhone>().firstOrNull()?.value ?: "",
            position = info.position,
            business = business.toMonitoredBusinessRef(space.name)
        )
    }
}