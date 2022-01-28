@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.portfolio

import bitframe.client.ReactUIScope
import pimonitor.PiMonitorViewModelConfig
import useViewModelState
import pimonitor.portfolio.PortfolioIntent as Intent
import pimonitor.portfolio.PortfolioState as State

class PortfolioReactScope(
    config: PiMonitorViewModelConfig
) : PortfolioScope(config), ReactUIScope<Intent, State> {
    override val useStateFromViewModel: () -> State = {
        useViewModelState(viewModel)
    }
}