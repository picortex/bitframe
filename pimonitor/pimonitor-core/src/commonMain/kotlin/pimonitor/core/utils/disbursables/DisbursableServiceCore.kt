@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package pimonitor.core.utils.disbursables

import bitframe.core.Identified
import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import pimonitor.core.utils.disbursables.filters.DisbursableFilter
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface DisbursableServiceCore<out D : Disbursable, out DS : DisbursableSummary> {
    @JsName("_ignore_load")
    fun load(rb: RequestBody.Authorized<String>): Later<D>

    /**
     * @param rb takes in a [RequestBody.Authorized] of a businessId
     */
    @JsName("_ignore_all")
    fun all(rb: RequestBody.Authorized<DisbursableFilter>): Later<List<DS>>

    @JsName("_ignore_delete")
    fun delete(rb: RequestBody.Authorized<Array<out String>>): Later<List<D>>

    @JsName("_ignore_createDisbursement")
    fun createDisbursement(rb: RequestBody.Authorized<DisbursableDisbursementParams>): Later<Disbursement>

    @JsName("_ignore_deleteDisbursement")
    fun deleteDisbursement(rb: RequestBody.Authorized<Identified<Array<String>>>): Later<List<Disbursement>>

    @JsName("_ignore_updateDisbursement")
    fun updateDisbursement(rb: RequestBody.Authorized<Identified<DisbursableDisbursementParams>>): Later<Disbursement>
}