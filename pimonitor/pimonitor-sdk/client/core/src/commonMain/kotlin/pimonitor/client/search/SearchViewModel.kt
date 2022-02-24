package pimonitor.client.search

import bitframe.client.UIScopeConfig
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
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
        is SearchIntent.Search -> search(i)
    }

    private fun clearSearch() {
        currentSearchJob?.cancel()
        ui.value = SearchState(loading = null, results = emptyList())
    }

    private fun CoroutineScope.search(i: SearchIntent.Search) {
        currentSearchJob?.cancel()
        ui.value = SearchState(
            loading = Feedback.Loading("Searching for ${i.key}"),
            results = searchCache[i.key] ?: emptyList()
        )
        currentSearchJob = launch {
            delay(debounceTime)
            ui.value = SearchState(
                loading = null,
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