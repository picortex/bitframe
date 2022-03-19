@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.portfolio

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.core.portfolio.PortfolioServiceCore
import pimonitor.client.portfolio.PortfolioIntent as Intent
import pimonitor.client.portfolio.PortfolioState as State
import kotlin.js.JsExport

open class PortfolioScope(
    override val config: UIScopeConfig<PortfolioService>
) : UIScope<State> {
    override val viewModel by lazy { PortfolioViewModel(config) }

    val loadPortfolio = { viewModel.post(Intent.LoadPortfolioData) }
}