package pimonitor.core.search

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface SearchRawParams {
    val key: String
}

fun SearchRawParams.toSearchParams() = SearchParams(requiredNotBlank(::key))