package pimonitor.client.search

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import viewmodel.ViewModel

class SearchViewModel(
    private val config: UIScopeConfig<SearchService>
) : ViewModel<SearchIntent, SearchState>(SearchState(), config.viewModel) {
    override fun CoroutineScope.execute(i: SearchIntent): Any {
        TODO("Not yet implemented")
    }
}