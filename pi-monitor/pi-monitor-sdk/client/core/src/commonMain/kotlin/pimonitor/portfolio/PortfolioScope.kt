@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.portfolio

import pimonitor.PiMonitorScopeConfig
import pimonitor.api.PiMonitorApi
import kotlin.js.JsExport
import pimonitor.portfolio.PortfolioIntent as Intent
import pimonitor.portfolio.PortfolioState as State

open class PortfolioScope(config: PiMonitorScopeConfig) : UIScope<Intent, State> {
    override val api: PiMonitorApi = config.api
    override val viewModel by lazy { PortfolioViewModel(config) }

    val loadPortfolio = { viewModel.post(Intent.LoadPortfolio) }
}