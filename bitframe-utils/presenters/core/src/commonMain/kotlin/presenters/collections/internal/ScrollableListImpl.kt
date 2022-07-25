@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections.internal

import presenters.collections.*
import viewmodel.ViewModelConfig
import kotlin.js.JsExport

class ScrollableListImpl<T>(
    override val paginator: Paginator<T>,
    override val selector: Selector<T>,
    override val actionManager: ActionManager,
    config: ViewModelConfig<*>
) : PageableImpl<T>(config), ScrollableList<T>,
    Paginator<T> by paginator,
    Selector<T> by selector,
    ActionManager by actionManager {
}