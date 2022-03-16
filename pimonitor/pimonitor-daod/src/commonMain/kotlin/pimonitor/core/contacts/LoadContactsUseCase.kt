package pimonitor.core.contacts

import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.search.SearchResult

interface LoadContactsUseCase {
    fun all(rb: RequestBody.Authorized<ContactsFilter>): Later<List<SearchResult.ContactPersonSummary>>
}