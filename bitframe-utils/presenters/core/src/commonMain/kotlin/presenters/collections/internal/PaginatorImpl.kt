package presenters.collections.internal

import koncurrent.Fulfilled
import koncurrent.Later
import koncurrent.Rejected
import koncurrent.later.finally
import live.Live
import presenters.collections.Page
import presenters.collections.PagedState
import presenters.collections.Paginator
import viewmodel.ViewModel
import viewmodel.ViewModelConfig

class PaginatorImpl<out T>(
    override var capacity: Int,
    private val onPage: (no: Int, capacity: Int) -> Later<out Page<T>>
) : ViewModel<PagedState<T>>(ViewModelConfig().of(PagedState.UnLoaded)), Paginator<T> {
    override val live: Live<PagedState<T>> get() = ui

    override fun setPageCapacity(cap: Int) {
        capacity = cap
    }

    override fun next() = when (val state = ui.value) {
        is PagedState.Failure -> Later.reject(Throwable("Can't load next page because paginator is in a failure state"))
        is PagedState.LoadedPage -> when {
            state.page.isEmpty -> Later.resolve(state.page)
            state.page.items.size < state.page.capacity -> Later.resolve(state.page)
            else -> page(state.page.no + 1)
        }

        is PagedState.Loading -> Later.reject(Throwable("Can't load next page because paginator is still loading"))
        is PagedState.UnLoaded -> page(1)
    }

    override fun previous(): Later<out Page<T>> = when (val state = ui.value) {
        is PagedState.Failure -> Later.reject(Throwable("Can't load next page because paginator is in a failure state"))
        is PagedState.LoadedPage -> when {
            state.page.no > 1 -> page(state.page.no - 1)
            else -> Later.resolve(state.page)
        }

        is PagedState.Loading -> Later.reject(Throwable("Can't load next page because paginator is still loading"))
        is PagedState.UnLoaded -> page(1)
    }

    override fun page(no: Int): Later<out Page<T>> = onPage(no, capacity).finally {
        when (it) {
            is Fulfilled -> ui.value = PagedState.LoadedPage(it.value)
            is Rejected -> ui.value = PagedState.Failure(it.cause)
        }
    }

    override fun refresh(): Later<out Page<T>> = when (val state = ui.value) {
        is PagedState.Failure -> page(1)
        is PagedState.LoadedPage -> page(state.page.no)
        is PagedState.Loading -> Later.reject(Throwable("Can't load next page because paginator is still loading"))
        is PagedState.UnLoaded -> page(1)
    }

    override fun first(): Later<out Page<T>> = page(1)

    override fun last(): Later<out Page<T>> = page(-1)
}