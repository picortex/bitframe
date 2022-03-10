@file:JsExport

package pimonitor.client.search

import kotlin.js.JsExport

sealed class SearchFeedback {
    object Pending : SearchFeedback()
    object Searching : SearchFeedback()
    object Completed : SearchFeedback()
}