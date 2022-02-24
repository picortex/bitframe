@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.portfolio

import pimonitor.core.portfolio.PortfolioData
import presenters.feedbacks.Feedback
import kotlin.js.JsExport
import kotlin.jvm.JvmField

data class PortfolioState(
    val status: Feedback = INITIAL_LOADING_STATUS,
    val data: PortfolioData = PortfolioData()
) {
    companion object {
        @JvmField
        val INITIAL_LOADING_STATUS = Feedback.Loading("Loading your portfolio data, please wait . . .")
    }
}