@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.picortex

import bitframe.core.RequestBody
import later.Later
import pimonitor.core.sage.AcceptSageOneInviteRawParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface PiCortexDashboardServiceCore {
    @JsName("_ignore_acceptInvite")
    fun acceptInvite(rb: RequestBody.UnAuthorized<AcceptPicortexInviteParams>): Later<AcceptPicortexInviteParams>
}