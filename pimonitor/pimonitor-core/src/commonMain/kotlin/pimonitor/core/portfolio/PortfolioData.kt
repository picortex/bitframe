@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.portfolio

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import presenters.cards.ValueCard
import kotlin.js.JsExport

data class PortfolioData(
    val cards: List<ValueCard> = emptyList(),
    val profileProgress: ProfileProgress = ProfileProgress(),
)