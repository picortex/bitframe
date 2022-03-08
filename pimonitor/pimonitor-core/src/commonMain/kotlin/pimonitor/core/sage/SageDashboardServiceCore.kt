@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.sage

import bitframe.core.RequestBody
import later.Later
import pimonitor.core.sage.AcceptSageOneInviteRawParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface SageDashboardServiceCore {
    @JsName("_ignore_acceptInvite")
    fun acceptInvite(rb: RequestBody.UnAuthorized<AcceptSageOneInviteParams>): Later<AcceptSageOneInviteParams>
}