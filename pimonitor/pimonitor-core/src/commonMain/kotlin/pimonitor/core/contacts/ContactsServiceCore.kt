package pimonitor.core.contacts

import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later

interface ContactsServiceCore {
    fun all(rb: RequestBody.Authorized<ContactsFilter>) : Later<List<ContactPersonSummary>>
}