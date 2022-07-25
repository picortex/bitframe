@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections.internal

import presenters.collections.*
import viewmodel.ViewModelConfig
import kotlin.js.JsExport

class ScrollableListImpl<T>(
    override val paginator: Paginator<T>,
    private val config: ViewModelConfig<*>,
    override val selector: SelectorImpl<T>,
    override val actionManager: ActionManager
) : PageableImpl<T>(config), ScrollableList<T>,
    Paginator<T> by paginator,
    Selector<T> by selector,
    ActionManager by actionManager {
    override fun map(paginator: Paginator<T>) = ScrollableListImpl(
        paginator, config, selector.map(paginator), actionManager
    )
}