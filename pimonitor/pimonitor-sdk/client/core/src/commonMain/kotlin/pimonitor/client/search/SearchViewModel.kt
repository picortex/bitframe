package pimonitor.client.search

import bitframe.client.UIScopeConfig
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.coroutines.*
import later.await
import pimonitor.core.search.SearchParams
import pimonitor.core.search.SearchResult
import viewmodel.ViewModel

class SearchViewModel(
    private val config: UIScopeConfig<SearchService>
) : ViewModel<SearchIntent, SearchState>(SearchState.Pending, config.viewModel) {
    private val service get() = config.service
    private val debounceTime = 2000L
    private val cache = config.service.config.cache
    private var currentSearchJob: Job? = null

    companion object {
        private const val CACHE_PREFIX = "search"
    }

    override fun CoroutineScope.execute(i: SearchIntent): Any = when (i) {
        SearchIntent.ClearSearch -> clearSearch()
        is SearchIntent.Search -> search(i)
    }

    private fun clearSearch() {
        currentSearchJob?.cancel()
        ui.value = SearchState.Pending
    }

    private fun CoroutineScope.search(i: SearchIntent.Search) {
        if (i.key.isBlank()) return
        currentSearchJob?.cancel()
        currentSearchJob = launch {
            val cachedResults = cache.loadOrNull<List<SearchResult>>("$CACHE_PREFIX:${i.key}").await()
            ui.value = SearchState.Searching(
                results = cachedResults ?: emptyList()
            )
            if (i.mode == SearchMode.DEBOUNCING) delay(debounceTime)
            val freshResults = try {
                service.search(SearchParams(i.key)).await()
            } catch (err: Throwable) {
                emptyList()
            }
            ui.value = SearchState.Completed(freshResults)
            cache.save("$CACHE_PREFIX:${i.key}", freshResults)
        }
    }
}