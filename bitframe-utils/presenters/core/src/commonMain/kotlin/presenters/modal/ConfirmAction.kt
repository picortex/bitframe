@file:JsExport

package presenters.modal

import kotlin.js.JsExport

data class ConfirmAction(
    val name: String,
    val handler: () -> Unit
)