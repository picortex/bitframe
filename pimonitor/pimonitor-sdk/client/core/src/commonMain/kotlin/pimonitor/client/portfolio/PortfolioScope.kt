package pimonitor.client.portfolio

import bitframe.client.MicroScope
import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.core.portfolio.PortfolioServiceCore
import pimonitor.client.portfolio.PortfolioIntent as Intent
import pimonitor.client.portfolio.PortfolioState as State
import kotlin.js.JsExport

internal fun PortfolioScope(config: UIScopeConfig<PortfolioService>) = MicroScope {
    viewModel(PortfolioViewModel(config))
    intents(PortfolioIntents(viewModel))
}