package pimonitor.client.search

import bitframe.client.UIScopeConfig
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.coroutines.*
import later.await
import pimonitor.core.search.SearchParams
import pimonitor.core.search.SearchResult
import viewmodel.ViewModel

class SearchViewModel(
    private val config: UIScopeConfig<SearchService>
) : ViewModel<SearchIntent, SearchState>(SearchState(), config.viewModel) {
    private val service get() = config.service
    private val debounceTime = 2000L
    private val searchCache = mutableMapOf<String, List<SearchResult>>()
    private var currentSearchJob: Job? = null

    override fun CoroutineScope.execute(i: SearchIntent): Any = when (i) {
        SearchIntent.ClearSearch -> clearSearch()
        is SearchIntent.Search -> search(i)
    }

    private fun clearSearch() {
        currentSearchJob?.cancel()
        ui.value = SearchState()
    }

    private fun CoroutineScope.search(i: SearchIntent.Search) {
        if (i.key.isBlank()) return
        currentSearchJob?.cancel()
        val state = ui.value.copy(
            field = ui.value.field.copy(value = i.key),
        )
        ui.value = state.copy(
            status = SearchFeedback.Searching,
            results = searchCache[i.key] ?: emptyList()
        )
        currentSearchJob = launch {
            if (i.mode == SearchMode.DEBOUNCING) delay(debounceTime)
            ui.value = state.copy(
                status = SearchFeedback.Completed,
                results = try {
                    service.search(SearchParams(i.key)).await()
                } catch (err: Throwable) {
                    emptyList()
                }
            )
            searchCache[i.key] = ui.value.results
        }
    }
}