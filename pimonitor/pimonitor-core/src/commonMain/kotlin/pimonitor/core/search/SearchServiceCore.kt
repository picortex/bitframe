@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.search

import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface SearchServiceCore {
    @JsName("_ignore_search")
    fun search(rb: RequestBody.Authorized<SearchParams>): Later<List<SearchResult>>
}