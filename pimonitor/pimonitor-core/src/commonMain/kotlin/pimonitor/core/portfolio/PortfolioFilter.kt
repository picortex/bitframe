@file:JsExport

package pimonitor.core.portfolio

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class PortfolioFilter(
    val key: String = ""
)