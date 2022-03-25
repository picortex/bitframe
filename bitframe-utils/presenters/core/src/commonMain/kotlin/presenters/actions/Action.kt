@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.actions

import kotlin.js.JsExport

@JsExport
sealed interface Action {
    val name: String
    val handler: Function<Unit>
}