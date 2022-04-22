@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.cases

import kotlinx.collections.interoperable.emptyList
import presenters.table.Table
import presenters.table.builders.tableOf
import kotlin.js.JsExport

data class CentralContent<out C, D>(
    val context: C,
    val table: Table<D> = tableOf(emptyList()) {}
)