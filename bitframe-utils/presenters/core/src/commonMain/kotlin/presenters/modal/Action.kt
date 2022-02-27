@file:JsExport

package presenters.modal

import kotlin.js.JsExport

data class Action(
    val name: String,
    val handler: () -> Unit
)