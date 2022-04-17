@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.utils.disbursables

import bitframe.core.IdentifiedRaw
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementRawParams
import kotlin.js.JsExport

@JsExport
interface DisbursableService<out D : Disbursable> {
    fun load(disbursableId: String): Later<Disbursable>
    fun createDisbursement(params: DisbursableDisbursementParams): Later<Disbursement>
    fun deleteDisbursements(params: IdentifiedRaw<Array<String>>): Later<List<Disbursement>>
    fun updateDisbursement(params: IdentifiedRaw<DisbursableDisbursementRawParams>): Later<Disbursement>
}