@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections.internal

import presenters.collections.*
import viewmodel.ViewModelConfig
import kotlin.js.JsExport

open class TableImpl<T>(
    override val paginator: Paginator<T>,
    private val config: TableConfig<T>
) : PagedImpl<T>(paginator, config), Table<T>, Paginator<T> by paginator {
    override val columns: Array<Column<T>> by lazy { config.columns }

    override fun map(paginator: Paginator<T>) = TableImpl(
        paginator, config
    )
}