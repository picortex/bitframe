@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.portfolio

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import useViewModelState
import viewmodel.asState
import pimonitor.client.portfolio.PortfolioIntent as Intent
import pimonitor.client.portfolio.PortfolioState as State

class PortfolioReactScope(
    override val config: UIScopeConfig<PortfolioService>
) : PortfolioScope(config), ReactUIScope<State> {
    override val useScopeState = { viewModel.asState() }
}