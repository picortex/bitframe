@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.operations

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.core.RequestBody
import later.await
import later.later
import pimonitor.core.business.operations.BusinessOperationsServiceCore
import pimonitor.core.business.utils.info.LoadInfoRawParams
import pimonitor.core.business.utils.info.toValidatedParams
import kotlin.js.JsExport

abstract class BusinessOperationsService(
    private val config: ServiceConfig
) : BusinessOperationsServiceCore {
    fun dashboard(params: LoadInfoRawParams) = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load operational dashboard info for business with id=${params.businessId}"),
            data = params.toValidatedParams()
        )
        dashboard(rb).await()
    }
}