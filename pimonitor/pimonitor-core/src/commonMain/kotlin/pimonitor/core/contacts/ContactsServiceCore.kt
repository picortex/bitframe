package pimonitor.core.contacts

import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.search.SearchResult
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface ContactsServiceCore {
    @JsName("_ignore_all")
    fun all(rb: RequestBody.Authorized<ContactsFilter>): Later<List<SearchResult.ContactPersonSummary>>
}