@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.search

import bitframe.client.ServiceConfig
import bitframe.core.RequestBody
import bitframe.core.Session
import kotlinx.collections.interoperable.List
import later.Later
import later.await
import later.later
import pimonitor.core.search.SearchRawParams
import pimonitor.core.search.SearchResult
import pimonitor.core.search.SearchServiceCore
import pimonitor.core.search.toSearchParams
import kotlin.js.JsExport

abstract class SearchService(open val config: ServiceConfig) : SearchServiceCore {
    fun search(params: SearchRawParams): Later<List<SearchResult>> = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.session.value as? Session.SignedIn ?: error("You must be logged in to search"),
            data = params.toSearchParams()
        )
        search(rb).await()
    }
}