@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.investments

import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.investments.params.CreateInvestmentDisbursementParams
import pimonitor.core.investments.params.CreateInvestmentsParams
import pimonitor.core.business.utils.disbursements.Disbursement
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface InvestmentsServiceCore {
    @JsName("_ignore_capture")
    fun capture(rb: RequestBody.Authorized<CreateInvestmentsParams>): Later<InvestmentSummary>

    /**
     * @param rb takes in a [RequestBody.Authorized] of a businessId
     */
    @JsName("_ignore_all")
    fun all(rb: RequestBody.Authorized<InvestmentFilter>): Later<List<InvestmentSummary>>

    @JsName("_ignore_disburse")
    fun disburse(rb: RequestBody.Authorized<CreateInvestmentDisbursementParams>): Later<Disbursement>

    @JsName("_ignore_delete")
    fun delete(rb: RequestBody.Authorized<Array<out String>>): Later<List<Investment>>
}