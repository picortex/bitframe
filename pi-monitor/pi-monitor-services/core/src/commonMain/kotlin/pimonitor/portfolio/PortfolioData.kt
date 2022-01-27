package pimonitor.portfolio

import kotlinx.collections.interoperable.List
import presenters.cards.ValueCard

data class PortfolioData(
    val cards: List<ValueCard>,
    val profileProgress: ProfileProgress,
)