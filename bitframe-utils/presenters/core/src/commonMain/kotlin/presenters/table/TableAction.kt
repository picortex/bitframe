@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.table

import kotlin.js.JsExport

sealed class TableAction<out D> {
    abstract val name: String
    abstract val handler: Function<Unit>

    data class Primary(
        override val name: String,
        override val handler: () -> Unit
    ) : TableAction<Nothing>()

    data class SingleSelect<D>(
        override val name: String,
        override val handler: (Row<D>) -> Unit
    ) : TableAction<D>()

    data class MultiSelect<D>(
        override val name: String,
        override val handler: (Array<Row<D>>) -> Unit
    ) : TableAction<D>()
}