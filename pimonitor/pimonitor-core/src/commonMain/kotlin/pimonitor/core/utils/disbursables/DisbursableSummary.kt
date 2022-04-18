@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.utils.disbursables

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
abstract class DisbursableSummary : Disbursable() {
    abstract val businessName: String
}