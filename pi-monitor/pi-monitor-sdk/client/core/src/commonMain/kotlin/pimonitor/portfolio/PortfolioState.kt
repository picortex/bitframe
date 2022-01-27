@file:JsExport

package pimonitor.portfolio

import kotlin.js.JsExport
import presenters.feedbacks.FormFeedback.Failure as FeedbackFailure
import presenters.feedbacks.FormFeedback.Loading as FeedbackLoading

sealed class PortfolioState {
    data class Loading(
        override val message: String
    ) : PortfolioState(), FeedbackLoading

    data class Portfolio(
        val data: PortfolioData
    ) : PortfolioState()

    data class Failure(
        override val cause: Throwable,
        override val message: String = cause.message ?: FeedbackFailure.DEFAULT_MESSAGE,
    ) : PortfolioState(), FeedbackFailure
}