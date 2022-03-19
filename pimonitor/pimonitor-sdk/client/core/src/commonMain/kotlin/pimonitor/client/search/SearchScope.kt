@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.search

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import kotlin.js.JsExport

open class SearchScope(
    override val config: UIScopeConfig<SearchService>
) : UIScope<SearchState> {
    override val viewModel by lazy { SearchViewModel(config) }

    val searchDebouncing = { key: String ->
        viewModel.post(SearchIntent.Search(SearchMode.DEBOUNCING, key))
    }

    val searchImmediately = { key: String ->
        viewModel.post(SearchIntent.Search(SearchMode.IMMEDIATELY, key))
    }

    val clearResults = {
        viewModel.post(SearchIntent.ClearSearch)
    }
}