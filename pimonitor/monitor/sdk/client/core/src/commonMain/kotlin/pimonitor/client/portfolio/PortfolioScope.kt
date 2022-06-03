package pimonitor.client.portfolio

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig

internal fun PortfolioScope(config: UIScopeConfig<PortfolioService>) = MicroScope {
    viewModel(PortfolioViewModel(config))
    intents(PortfolioIntents(viewModel))
}