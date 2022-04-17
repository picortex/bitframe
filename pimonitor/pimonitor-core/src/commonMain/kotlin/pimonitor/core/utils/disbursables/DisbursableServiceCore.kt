@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.utils.disbursables

import bitframe.core.Identified
import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface DisbursableServiceCore<out D : Disbursable> {
    @JsName("_ignore_load")
    fun load(rb: RequestBody.Authorized<String>): Later<D>

    @JsName("_ignore_createDisbursement")
    fun createDisbursement(rb: RequestBody.Authorized<DisbursableDisbursementParams>): Later<Disbursement>

    @JsName("_ignore_deleteDisbursement")
    fun deleteDisbursement(rb: RequestBody.Authorized<Identified<Array<String>>>): Later<List<Disbursement>>

    @JsName("_ignore_updateDisbursement")
    fun updateDisbursement(rb: RequestBody.Authorized<Identified<DisbursableDisbursementParams>>): Later<Disbursement>
}