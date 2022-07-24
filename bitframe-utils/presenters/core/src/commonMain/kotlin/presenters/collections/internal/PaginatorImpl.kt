package presenters.collections.internal

import koncurrent.Fulfilled
import koncurrent.Later
import koncurrent.Rejected
import koncurrent.later.finally
import live.Live
import presenters.collections.Page
import presenters.collections.PageableState
import presenters.collections.Paginator
import viewmodel.ViewModel
import viewmodel.ViewModelConfig

class PaginatorImpl<out T>(
    override var capacity: Int,
    private val onPage: (no: Int, capacity: Int) -> Later<out Page<T>>
) : ViewModel<PageableState<T>>(ViewModelConfig().of(PageableState.UnLoaded)), Paginator<T> {
    override val live: Live<PageableState<T>> get() = ui

    override fun setPageCapacity(cap: Int) {
        capacity = cap
    }

    override fun loadNextPage() = when (val state = ui.value) {
        is PageableState.Failure -> Later.reject(Throwable("Can't load next page because paginator is in a failure state"))
        is PageableState.LoadedPage -> when {
            state.page.isEmpty -> Later.resolve(state.page)
            state.page.items.size < state.page.capacity -> Later.resolve(state.page)
            else -> loadPage(state.page.number + 1)
        }

        is PageableState.Loading -> Later.reject(Throwable("Can't load next page because paginator is still loading"))
        is PageableState.UnLoaded -> loadPage(1)
    }

    override fun loadPreviousPage(): Later<out Page<T>> = when (val state = ui.value) {
        is PageableState.Failure -> Later.reject(Throwable("Can't load next page because paginator is in a failure state"))
        is PageableState.LoadedPage -> when {
            state.page.number > 1 -> loadPage(state.page.number - 1)
            else -> Later.resolve(state.page)
        }

        is PageableState.Loading -> Later.reject(Throwable("Can't load next page because paginator is still loading"))
        is PageableState.UnLoaded -> loadPage(1)
    }

    override fun loadPage(no: Int): Later<out Page<T>> = onPage(no, capacity).finally {
        when (it) {
            is Fulfilled -> ui.value = PageableState.LoadedPage(it.value)
            is Rejected -> ui.value = PageableState.Failure(it.cause)
        }
    }

    override fun refresh(): Later<out Page<T>> = when (val state = ui.value) {
        is PageableState.Failure -> loadPage(1)
        is PageableState.LoadedPage -> loadPage(state.page.number)
        is PageableState.Loading -> Later.reject(Throwable("Can't load next page because paginator is still loading"))
        is PageableState.UnLoaded -> loadPage(1)
    }

    override fun loadFirstPage(): Later<out Page<T>> = loadPage(1)

    override fun loadLastPage(): Later<out Page<T>> = loadPage(-1)
}