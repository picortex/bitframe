@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.sage

import bitframe.client.ServiceConfig
import bitframe.core.RequestBody
import later.await
import later.later
import pimonitor.core.picortex.AcceptPicortexInviteRawParams
import pimonitor.core.picortex.PiCortexDashboardServiceCore
import pimonitor.core.sage.AcceptSageOneInviteRawParams
import pimonitor.core.sage.SageDashboardServiceCore
import pimonitor.core.sage.toValidatedInviteParams
import kotlin.js.JsExport

abstract class SageDashboardService(
    open val config: ServiceConfig
) : SageDashboardServiceCore {
    fun acceptInvite(params: AcceptSageOneInviteRawParams) = config.scope.later {
        val rb = RequestBody.UnAuthorized(config.appId, params.toValidatedInviteParams())
        acceptInvite(rb).await()
    }
}