@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections.internal

import kotlinx.collections.interoperable.List
import presenters.collections.*
import viewmodel.ViewModelConfig
import kotlin.js.JsExport

open class TableImpl<T>(
    override val paginator: PaginationManager<T>,
    override val selector: SelectionManager<T>,
    override val actionsManager: ActionsManager<T>,
    override val columns: List<Column<T>>,
    config: ViewModelConfig
) : PageableImpl<T>(config), Table<T>,
    PaginationManager<T> by paginator,
    SelectionManager<T> by selector,
    ActionsManager<T> by actionsManager {
}