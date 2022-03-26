@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package akkounts.reports.utils

import kash.Money
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class StatementEntryItem(
    val details: String,
    val value: Money
)