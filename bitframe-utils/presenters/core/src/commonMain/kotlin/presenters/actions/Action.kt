package presenters.actions

import kotlin.js.JsExport

@JsExport
sealed interface Action {
    val name: String
    val handler: Any
}