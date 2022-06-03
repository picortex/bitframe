@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.utils.disbursables

import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.client.utils.disbursables.disbursements.DisbursementsService
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.DisbursableSummary
import pimonitor.core.utils.disbursables.filters.DisbursableRawFilter
import kotlin.js.JsExport

@JsExport
interface DisbursableService<out D : Disbursable, out DS : DisbursableSummary> {
    fun load(disbursableId: String): Later<DS>
    fun delete(vararg ids: String): Later<List<D>>
    fun all(params: DisbursableRawFilter? = null): Later<List<DS>>
    val disbursements: DisbursementsService
}