@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.portfolio

import pimonitor.core.portfolio.PortfolioData
import presenters.feedbacks.Feedback
import kotlin.js.JsExport
import presenters.feedbacks.Feedback.Failure as FeedbackFailure
import presenters.feedbacks.Feedback.Loading as FeedbackLoading

sealed class PortfolioState {
    data class Status(val value: Feedback) : PortfolioState()

    data class Portfolio(val data: PortfolioData) : PortfolioState()
}