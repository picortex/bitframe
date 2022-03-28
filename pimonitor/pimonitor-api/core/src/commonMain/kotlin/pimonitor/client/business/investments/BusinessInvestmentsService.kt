@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.investments

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.core.RequestBody
import later.await
import later.later
import pimonitor.core.business.investments.BusinessInvestmentsServiceCore
import pimonitor.core.business.investments.CaptureInvestmentsRawParams
import pimonitor.core.business.investments.toValidatedCaptureInvestmentsParams
import kotlin.js.JsExport

abstract class BusinessInvestmentsService(
    private val config: ServiceConfig
) : BusinessInvestmentsServiceCore {

    fun capture(params: CaptureInvestmentsRawParams) = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("capture investments"),
            data = params.toValidatedCaptureInvestmentsParams()
        )
        capture(rb).await()
    }

    fun all(businessId: String) = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load investments for business"),
            data = businessId
        )
        all(rb).await()
    }
}