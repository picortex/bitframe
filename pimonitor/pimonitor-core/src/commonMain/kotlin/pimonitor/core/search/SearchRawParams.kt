package pimonitor.core.search

import kotlin.js.JsExport

@JsExport
interface SearchRawParams {
    val key: String
}

fun SearchRawParams.toSearchParams() = SearchParams(key)