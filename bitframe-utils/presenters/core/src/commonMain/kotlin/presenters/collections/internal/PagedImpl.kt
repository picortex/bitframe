@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections.internal

import presenters.collections.*
import viewmodel.ViewModel
import viewmodel.ViewModelConfig
import kotlin.js.JsExport

abstract class PagedImpl<T>(
    override val paginator: Paginator<T>,
    private val config: ViewModelConfig<*>
) : ViewModel<PagedState<T>>(config.of(PagedState.UnLoaded)), Paged<T> {
    fun initialize(page: Page<T>) {
        ui.value = PagedState.LoadedPage(page)
    }
}