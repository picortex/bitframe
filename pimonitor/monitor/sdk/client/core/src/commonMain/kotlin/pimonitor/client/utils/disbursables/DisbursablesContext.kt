@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.utils.disbursables

import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.utils.disbursables.DisbursableSummary
import kotlin.js.JsExport

data class DisbursablesContext<DS : DisbursableSummary>(
    val business: MonitoredBusinessBasicInfo,
    val disbursable: DS
)
