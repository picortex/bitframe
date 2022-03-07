@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.picortex

import bitframe.client.ServiceConfig
import bitframe.core.RequestBody
import later.await
import later.later
import pimonitor.core.picortex.AcceptPicortexInviteRawParams
import pimonitor.core.picortex.PiCortexDashboardServiceCore
import pimonitor.core.picortex.toValidatedInviteParams
import kotlin.js.JsExport

abstract class PiCortexDashboardService(
    open val config: ServiceConfig
) : PiCortexDashboardServiceCore {
    fun acceptInvite(params: AcceptPicortexInviteRawParams) = config.scope.later {
        val rb = RequestBody.UnAuthorized(config.appId, params.toValidatedInviteParams())
        acceptInvite(rb).await()
    }
}