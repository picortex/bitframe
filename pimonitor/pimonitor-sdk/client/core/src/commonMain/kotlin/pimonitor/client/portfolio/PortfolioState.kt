@file:Suppress("WRONG_EXPORTED_DECLARATION")

package pimonitor.client.portfolio

import pimonitor.core.portfolio.PortfolioData
import kotlin.js.JsExport
import presenters.feedbacks.Feedback.Failure as FeedbackFailure
import presenters.feedbacks.Feedback.Loading as FeedbackLoading

@JsExport
sealed interface PortfolioState {
    data class Loading(
        override val message: String
    ) : FeedbackLoading(message), PortfolioState

    data class Portfolio(
        val data: PortfolioData
    ) : PortfolioState

    data class Failure(
        override val cause: Throwable,
        override val message: String = cause.message ?: "Unknown error",
    ) : FeedbackFailure(cause, message), PortfolioState
}