@file:JsExport

package pimonitor.client.portfolio

import viewmodel.ViewModel
import kotlin.js.JsExport

class PortfolioIntents internal constructor(
    private val viewModel: ViewModel<PortfolioIntent, *>
) {
    val loadPortfolio = { viewModel.post(PortfolioIntent.LoadPortfolioData) }
}