@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.ServiceConfig
import bitframe.core.RequestBody
import bitframe.core.Session
import later.await
import later.later
import pimonitor.core.businesses.BusinessFilter
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.toValidatedCreateBusinessParams
import kotlin.js.JsExport
import pimonitor.core.businesses.BusinessesServiceCore

abstract class BusinessesService(
    open val config: ServiceConfig
) : BusinessesServiceCore {

    val logger get() = config.logger

    fun create(params: CreateMonitoredBusinessRawParams) = config.scope.later {
        logger.info("Registering business ${params.businessName}")
        val validatedParams = params.toValidatedCreateBusinessParams()
        val rb = RequestBody.Authorized(
            session = config.session.value as? Session.SignedIn ?: error("You must be signed in to be able to create a business"),
            data = validatedParams
        )
        val result = create(rb).await()
        val event = BusinessAddedEvent(
            data = result,
            spaceId = rb.session.space.uid
        )
        config.bus.dispatch(event)
        logger.info("Registration completed")
    }

    fun all() = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.session.value as? Session.SignedIn ?: error("You must be signed in to query businesses"),
            data = BusinessFilter("")
        )
        all(rb).await()
    }
}