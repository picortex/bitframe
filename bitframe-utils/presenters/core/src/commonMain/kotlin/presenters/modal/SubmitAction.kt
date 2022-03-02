@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.modal

import kotlin.js.JsExport

data class SubmitAction<in T>(
    val name: String,
    val handler: (T) -> Unit
)