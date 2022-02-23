@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.portfolio

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.serialization.Serializable
import presenters.cards.ValueCard
import kotlin.js.JsExport

@Serializable
data class PortfolioData(
    val cards: List<ValueCard<String>> = emptyList(),
    val profileProgress: ProfileProgress = ProfileProgress(),
)