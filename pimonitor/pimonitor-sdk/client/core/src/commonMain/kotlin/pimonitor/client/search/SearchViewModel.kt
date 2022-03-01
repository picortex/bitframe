package pimonitor.client.search

import bitframe.client.UIScopeConfig
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.coroutines.*
import later.await
import pimonitor.core.search.SearchParams
import pimonitor.core.search.SearchResult
import presenters.feedbacks.Feedback
import viewmodel.ViewModel

class SearchViewModel(
    private val config: UIScopeConfig<SearchService>
) : ViewModel<SearchIntent, SearchState>(SearchState(), config.viewModel) {
    private val service get() = config.service
    private val debounceTime = 3000L
    private val searchCache = mutableMapOf<String, List<SearchResult>>()
    private var currentSearchJob: Job? = null

    override fun CoroutineScope.execute(i: SearchIntent): Any = when (i) {
        SearchIntent.ClearSearch -> clearSearch()
        is SearchIntent.SearchDebouncing -> search(SearchMode.DEBOUNCING, i.key)
        is SearchIntent.SearchImmediately -> search(SearchMode.IMMEDIATELY, i.key)
    }

    private fun clearSearch() {
        currentSearchJob?.cancel()
        ui.value = SearchState(loading = null, results = emptyList())
    }

    enum class SearchMode {
        IMMEDIATELY, DEBOUNCING
    }

    private fun CoroutineScope.search(mode: SearchMode, key: String) {
        currentSearchJob?.cancel()
        ui.value = SearchState(
            loading = Feedback.Loading("Searching for $key"),
            results = searchCache[key] ?: emptyList()
        )
        currentSearchJob = launch {
            if (mode == SearchMode.DEBOUNCING) delay(debounceTime)
            ui.value = SearchState(
                loading = null,
                results = try {
                    service.search(SearchParams(key)).await()
                } catch (err: Throwable) {
                    emptyList()
                }
            )
            searchCache[key] = ui.value.results
        }
    }
}