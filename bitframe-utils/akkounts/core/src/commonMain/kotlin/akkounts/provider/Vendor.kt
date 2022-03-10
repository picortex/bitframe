@file:JsExport

package akkounts.provider

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * The Accounting Service Vendor
 *
 * e.g. QuickbooksOnline by Intuit Inc
 */
@Serializable
data class Vendor(
    val product: String,
    val company: String
)