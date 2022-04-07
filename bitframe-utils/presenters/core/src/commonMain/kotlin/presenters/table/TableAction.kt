@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.table

import kotlin.js.JsExport

sealed class TableAction<out D> {
    abstract val name: String
    abstract val handler: Any

    data class Primary(
        override val name: String,
        override val handler: () -> Unit
    ) : TableAction<Nothing>()

    data class SingleSelect<out D>(
        override val name: String,
        override val handler: (Row<@UnsafeVariance D>) -> Unit
    ) : TableAction<D>()

    data class MultiSelect<out D>(
        override val name: String,
        override val handler: (Array<Row<@UnsafeVariance D>>) -> Unit
    ) : TableAction<D>()

    val isPrimary by lazy { this is Primary }
    val asPrimary by lazy { this as Primary }

    val isSingleSelect by lazy { this is SingleSelect }
    val asSingleSelect by lazy { this as SingleSelect }

    val isMultiSelect by lazy { this is MultiSelect }
    val asMultiSelect by lazy { this as MultiSelect }
}