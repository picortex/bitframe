@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.investments

import bitframe.core.Identified
import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.investments.filters.InvestmentFilter
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.utils.disbursements.DisbursableServiceCore
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface InvestmentsServiceCore : DisbursableServiceCore {
    @JsName("_ignore_create")
    fun create(rb: RequestBody.Authorized<InvestmentParams>): Later<Investment>

    @JsName("_ignore_update")
    fun update(rb: RequestBody.Authorized<Identified<InvestmentParams>>): Later<Investment>

    /**
     * @param rb takes in a [RequestBody.Authorized] of a businessId
     */
    @JsName("_ignore_all")
    fun all(rb: RequestBody.Authorized<InvestmentFilter>): Later<List<InvestmentSummary>>

    @JsName("_ignore_delete")
    fun delete(rb: RequestBody.Authorized<Array<out String>>): Later<List<Investment>>
}