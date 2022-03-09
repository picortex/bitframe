@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.core.RequestBody
import bitframe.core.Session
import later.Later
import later.await
import later.later
import pimonitor.core.businesses.BusinessFilter
import kotlin.js.JsExport
import pimonitor.core.businesses.BusinessesServiceCore
import pimonitor.core.businesses.params.*

abstract class BusinessesService(
    open val config: ServiceConfig
) : BusinessesServiceCore {

    val logger get() = config.logger

    fun create(params: CreateMonitoredBusinessRawParams) = config.scope.later {
        logger.info("Registering business ${params.businessName}")
        val validatedParams = params.toValidatedCreateBusinessParams()
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("create a business"),
            data = validatedParams
        )
        val result = create(rb).await()
        config.bus.dispatch(BusinessAddedEvent(data = result, spaceId = rb.session.space.uid))
        logger.info("Registration completed")
        result
    }

    fun invite(params: InviteToShareReportsRawParams) = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("send an invite to ${params.to}"),
            data = params.toValidatedInviteToShareReportParams()
        )
        invite(rb).await()
    }

    fun all() = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("query businesses"),
            data = BusinessFilter("")
        )
        all(rb).await()
    }

    fun delete(vararg monitorIds: String) = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("delete business(es)"),
            data = monitorIds
        )
        delete(rb).await()
    }

    fun defaultInviteMessage(params: InviteMessageRawParams): Later<String> = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("prepare invite form"),
            data = params.toValidatedInviteMessageParams()
        )
        defaultInviteMessage(rb).await()
    }
}