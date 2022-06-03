@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.investments

import bitframe.core.Identified
import bitframe.core.RequestBody
import later.Later
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.utils.disbursables.DisbursableServiceCore
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface InvestmentsServiceCore : DisbursableServiceCore<Investment, InvestmentSummary> {
    @JsName("_ignore_create")
    fun create(rb: RequestBody.Authorized<InvestmentParams>): Later<Investment>

    @JsName("_ignore_update")
    fun update(rb: RequestBody.Authorized<Identified<InvestmentParams>>): Later<Investment>
}