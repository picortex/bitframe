@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.portfolio

import pimonitor.core.portfolio.MonitoredBusinessPortfolio
import presenters.cases.Feedback
import kotlin.js.JsExport
import kotlin.jvm.JvmField

data class PortfolioState(
    val status: Feedback = INITIAL_LOADING_STATUS,
    val data: MonitoredBusinessPortfolio = MonitoredBusinessPortfolio()
) {
    companion object {
        @JvmField
        val INITIAL_LOADING_STATUS = Feedback.Loading("Loading your portfolio data, please wait . . .")
    }
}