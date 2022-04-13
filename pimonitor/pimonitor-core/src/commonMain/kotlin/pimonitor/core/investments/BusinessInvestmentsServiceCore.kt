@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.investments

import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.investments.params.InvestmentDisbursementParams
import pimonitor.core.investments.params.InvestmentsParams
import pimonitor.core.business.utils.disbursements.Disbursement
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface BusinessInvestmentsServiceCore {
    @JsName("_ignore_capture")
    fun capture(rb: RequestBody.Authorized<InvestmentsParams>): Later<Investment>

    /**
     * @param rb takes in a [RequestBody.Authorized] of a businessId
     */
    @JsName("_ignore_all")
    fun all(rb: RequestBody.Authorized<String>): Later<List<Investment>>

    @JsName("_ignore_disburse")
    fun disburse(rb: RequestBody.Authorized<InvestmentDisbursementParams>): Later<Disbursement>
}