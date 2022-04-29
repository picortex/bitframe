@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.portfolio

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.serialization.Serializable
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.cards.ValueCard
import kotlin.js.JsExport

@Serializable
data class MonitoredBusinessPortfolio(
    val business: MonitoredBusinessBasicInfo,
    val cards: List<ValueCard<String>> = emptyList(),
    val profileProgress: ProfileProgress = ProfileProgress(),
)