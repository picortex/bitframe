package pimonitor.portfolio

import bitframe.client.UIScope
import pimonitor.PiMonitorViewModelConfig
import pimonitor.api.PiMonitorService
import pimonitor.portfolio.PortfolioIntent as Intent
import pimonitor.portfolio.PortfolioState as State

open class PortfolioScope(config: PiMonitorViewModelConfig) : UIScope<Intent, State> {
    override val service: PiMonitorService = config.service
    override val viewModel by lazy { PortfolioViewModel(config) }

    val loadPortfolio = { viewModel.post(Intent.LoadPortfolio) }
}