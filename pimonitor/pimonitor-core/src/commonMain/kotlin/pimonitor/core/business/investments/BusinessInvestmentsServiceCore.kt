@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.investments

import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.business.investments.CaptureInvestmentsParams
import pimonitor.core.invites.Invite
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface BusinessInvestmentsServiceCore {
    @JsName("_ignore_capture")
    fun capture(rb: RequestBody.Authorized<CaptureInvestmentsParams>): Later<Investment>

    /**
     * @param rb takes in a [RequestBody.Authorized] of a businessId
     */
    @JsName("_ignore_all")
    fun all(rb: RequestBody.Authorized<String>): Later<List<Investment>>
}