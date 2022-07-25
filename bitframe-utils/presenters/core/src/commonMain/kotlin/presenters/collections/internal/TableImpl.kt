@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections.internal

import presenters.collections.*
import viewmodel.ViewModelConfig
import kotlin.js.JsExport

open class TableImpl<T>(
    override val paginator: Paginator<T>,
    override val selector: Selector<T>,
    override val actionManager: ActionManager,
    override val columns: Array<Column<T>>,
    config: ViewModelConfig<*>
) : PageableImpl<T>(config), Table<T>,
    Paginator<T> by paginator,
    Selector<T> by selector,
    ActionManager by actionManager {
}