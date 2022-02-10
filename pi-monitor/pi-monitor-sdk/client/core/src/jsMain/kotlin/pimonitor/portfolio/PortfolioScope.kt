@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.portfolio

import bitframe.client.UIScope
import pimonitor.PiMonitorScopeConfig
import pimonitor.api.PiMonitorService
import pimonitor.portfolio.PortfolioIntent as Intent
import pimonitor.portfolio.PortfolioState as State

open class PortfolioScope(config: PiMonitorScopeConfig) : UIScope<Intent, State> {
    override val service: PiMonitorService = config.service
    override val viewModel by lazy { PortfolioViewModel(config) }

    val loadPortfolio = { viewModel.post(Intent.LoadPortfolio) }
}