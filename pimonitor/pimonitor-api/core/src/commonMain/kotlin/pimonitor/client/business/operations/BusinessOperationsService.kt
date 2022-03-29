@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.operations

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.core.RequestBody
import later.await
import later.later
import pimonitor.core.business.operations.BusinessOperationsServiceCore
import pimonitor.core.business.utils.params.LoadReportRawParams
import pimonitor.core.business.utils.params.toValidatedParams
import kotlin.js.JsExport

abstract class BusinessOperationsService(
    private val config: ServiceConfig
) : BusinessOperationsServiceCore {
    fun dashboard(params: LoadReportRawParams) = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load operational dashboard info for business with id=${params.businessId}"),
            data = params.toValidatedParams()
        )
        dashboard(rb).await()
    }
}