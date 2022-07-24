@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections.internal

import presenters.collections.*
import kotlin.js.JsExport

open class TableImpl<T>(
    override val paginator: Paginator<T>,
    private val config: TableConfig<T>,
    override val selector: SelectorImpl<T>,
    override val actionManager: ActionManager
) : PageableImpl<T>(config), Table<T>,
    Paginator<T> by paginator,
    Selector<T> by selector,
    ActionManager by actionManager {
    override val columns: Array<Column<T>> by lazy { config.columns }

    override fun map(paginator: Paginator<T>) = TableImpl(
        paginator, config, selector.map(paginator), actionManager
    )
}