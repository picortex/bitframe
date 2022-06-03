@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package pimonitor.core.utils.disbursables

import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.utils.disbursables.disbursements.DisbursementsServiceCore
import pimonitor.core.utils.disbursables.filters.DisbursableFilter
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface DisbursableServiceCore<out D : Disbursable, out DS : DisbursableSummary> {
    @JsName("_ignore_load")
    fun load(rb: RequestBody.Authorized<String>): Later<DS>

    /**
     * @param rb takes in a [RequestBody.Authorized] of a businessId
     */
    @JsName("_ignore_all")
    fun all(rb: RequestBody.Authorized<DisbursableFilter>): Later<List<DS>>

    @JsName("_ignore_delete")
    fun delete(rb: RequestBody.Authorized<Array<out String>>): Later<List<D>>

    val disbursements: DisbursementsServiceCore
}