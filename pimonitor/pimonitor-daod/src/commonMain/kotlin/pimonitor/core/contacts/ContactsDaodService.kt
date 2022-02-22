package pimonitor.core.contacts

import bitframe.core.*
import kotlinx.collections.interoperable.List
import later.Later
import later.await
import later.later
import pimonitor.core.businesses.MonitorBusinessBasicInfo
import pimonitor.core.businesses.MonitoredBusinessBasicInfo

open class ContactsDaodService(
    open val config: DaodServiceConfig
) : ContactsServiceCore {
    val businessDao by lazy { config.daoFactory.get<MonitorBusinessBasicInfo>() }
    val usersDao by lazy { config.daoFactory.get<User>() }
    val contactsDao by lazy { config.daoFactory.get<ContactPersonSpaceInfo>() }
    override fun all(rb: RequestBody.Authorized<ContactsFilter>): Later<List<ContactPersonSummary>> = config.scope.later {
        val session = rb.session
        val condition = MonitoredBusinessBasicInfo::owningSpaceId isEqualTo session.space.uid
        businessDao.all(condition).await().map {

        }
        TODO()
    }
}