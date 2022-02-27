@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.modal

import kotlinx.collections.interoperable.List
import kotlin.js.JsExport

data class Dialog<out T>(
    val heading: String,
    val details: String,
    val content: T,
    val actions: List<Action>
)