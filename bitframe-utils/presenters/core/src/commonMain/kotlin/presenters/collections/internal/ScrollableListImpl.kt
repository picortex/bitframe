@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections.internal

import presenters.collections.Paginator
import presenters.collections.ScrollableList
import viewmodel.ViewModelConfig
import kotlin.js.JsExport

class ScrollableListImpl<T>(
    override val paginator: Paginator<T>,
    private val config: ViewModelConfig<*>
) : PagedImpl<T>(paginator, config), ScrollableList<T> {
    override fun map(paginator: Paginator<T>) = ScrollableListImpl(paginator, config)
}