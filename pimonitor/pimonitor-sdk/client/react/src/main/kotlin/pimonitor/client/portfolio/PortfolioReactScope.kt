@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.portfolio

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import useViewModelState
import pimonitor.client.portfolio.PortfolioIntent as Intent
import pimonitor.client.portfolio.PortfolioState as State

@JsExport
class PortfolioReactScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : PortfolioScope(config), ReactUIScope<Intent, State> {
    override val useScopeState: () -> State = {
        useViewModelState(viewModel)
    }
}