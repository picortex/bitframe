@file:JsExport

package presenters.collections.table

import presenters.collections.Table
import kotlin.js.JsExport

data class GlobalAction<D>(
    val name: String,
    val handler: (Table.State<D>) -> Unit
)