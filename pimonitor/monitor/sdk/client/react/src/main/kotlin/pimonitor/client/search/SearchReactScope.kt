@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.search

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import viewmodel.asState

class SearchReactScope(
    override val config: UIScopeConfig<SearchService>
) : SearchScope(config), ReactUIScope<SearchState> {
    override val useScopeState = { viewModel.asState() }
}