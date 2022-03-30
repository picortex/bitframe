package pimonitor.core.search

import bitframe.core.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.encodeToString
import later.Later
import later.await
import later.later
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.contacts.ContactsFilter
import pimonitor.core.contacts.LoadContactsUseCase
import pimonitor.core.contacts.LoadContactsUseCaseImpl

open class SearchServiceDaod(
    open val config: ServiceConfigDaod
) : SearchServiceCore, LoadContactsUseCase by LoadContactsUseCaseImpl(config) {

    private val monitoredBusinessDao by lazy { config.daoFactory.get<MonitoredBusinessBasicInfo>() }

    override fun search(rb: RequestBody.Authorized<SearchParams>): Later<List<SearchResult>> = config.scope.later {
        val businesses = searchBusinesses(rb)
        val contacts = searchContacts(rb)
        (businesses.await() + contacts.await()).toInteroperableList()
    }

    fun searchBusinesses(rb: RequestBody.Authorized<SearchParams>) = config.scope.later {
        val session = rb.session
        val params = rb.data
        monitoredBusinessDao.all(
            MonitoredBusinessBasicInfo::owningSpaceId isEqualTo session.space.uid
        ).await().toTypedArray().filter {
            it.address.contains(params.key, ignoreCase = true) || it.email.contains(params.key, ignoreCase = true) || it.name.contains(params.key, ignoreCase = true)
        }.map {
            SearchResult.MonitoredBusiness(
                name = it.name, address = it.address, email = it.email, uid = it.uid
            )
        }
    }

    fun searchContacts(rb: RequestBody.Authorized<SearchParams>) = config.scope.later {
        all(rb.map { ContactsFilter("") }).await().filter {
            config.json.encodeToString(it).contains(rb.data.key, ignoreCase = true)
        }
    }
}