@file:JsExport

package akkounts.reports.utils

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class StatementEntryItem(
    val details: String,
    val amount: Int
)