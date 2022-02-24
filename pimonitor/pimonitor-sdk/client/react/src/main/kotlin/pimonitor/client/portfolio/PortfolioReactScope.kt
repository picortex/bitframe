@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.portfolio

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import useViewModelState
import pimonitor.client.portfolio.PortfolioIntent as Intent
import pimonitor.client.portfolio.PortfolioState as State

class PortfolioReactScope(
    override val config: UIScopeConfig<PortfolioService>
) : PortfolioScope(config), ReactUIScope<Intent, State> {
    override val useScopeState: () -> State = {
        useViewModelState(viewModel)
    }
}