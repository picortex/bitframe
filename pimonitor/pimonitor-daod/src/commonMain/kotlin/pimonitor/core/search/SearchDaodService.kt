package pimonitor.core.search

import bitframe.core.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.encodeToString
import later.Later
import later.await
import later.later
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.contacts.ContactPersonSpaceInfo
import pimonitor.core.contacts.ContactsFilter
import pimonitor.core.contacts.LoadContactsUseCase
import pimonitor.core.contacts.LoadContactsUseCaseImpl

open class SearchDaodService(
    open val config: DaodServiceConfig
) : SearchServiceCore, LoadContactsUseCase by LoadContactsUseCaseImpl(config) {

    private val monitoredBusinessDao by lazy { config.daoFactory.get<MonitoredBusinessBasicInfo>() }
    private val spacesDao by lazy { config.daoFactory.get<Space>() }

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
        ).await().toTypedArray().map {
            it to spacesDao.load(it.spaceId).await()
        }.filter {
            it.first.address.contains(params.key, ignoreCase = true)
                    || it.first.email.contains(params.key, ignoreCase = true)
                    || it.second.name.contains(params.key, ignoreCase = true)
        }.map {
            SearchResult.MonitoredBusiness(
                name = it.second.name,
                address = it.first.address,
                spaceId = it.second.uid,
                email = it.first.email
            )
        }
    }

    fun searchContacts(rb: RequestBody.Authorized<SearchParams>) = config.scope.later {
        all(rb.map { ContactsFilter("") }).await().filter {
            config.json.encodeToString(it).contains(rb.data.key, ignoreCase = true)
        }
    }
}