@file:JsExport

package pimonitor.core.portfolio

import kotlinx.collections.interoperable.List
import presenters.cards.ValueCard
import kotlin.js.JsExport

data class PortfolioData(
    val cards: List<ValueCard>,
    val profileProgress: ProfileProgress,
)