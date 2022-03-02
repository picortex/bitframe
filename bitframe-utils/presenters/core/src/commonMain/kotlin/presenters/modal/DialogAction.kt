@file:JsExport

package presenters.modal

import kotlin.js.JsExport

data class DialogAction(
    val name: String,
    val handler: () -> Unit
)