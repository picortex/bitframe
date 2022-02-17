@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.ServiceConfig
import bitframe.core.RequestBody
import bitframe.core.Session
import later.await
import later.later
import pimonitor.core.businesses.params.RawCreateBusinessParams
import pimonitor.core.businesses.params.toValidatedCreateBusinessParams
import kotlin.js.JsExport
import pimonitor.core.businesses.BusinessesService as CoreBusinessesService

abstract class BusinessesService(
    override val config: ServiceConfig
) : CoreBusinessesService {

    val logger get() = config.logger

    companion object {
        const val CREATE_BUSINESS_EVENT_ID = "pimonitor.evaluation.business.create"
//        fun createBusinessEvent(business: MonitoredBusiness) = Event(business,CREATE_BUSINESS_EVENT_ID )
    }

    fun create(params: RawCreateBusinessParams) = config.scope.later {
        logger.info("Registering business ${params.businessName}")
        val rb = RequestBody.Authorized(
            session = config.session.value as? Session.SignedIn ?: error("You must be signed in to be able to create a business"),
            data = params.toValidatedCreateBusinessParams()
        )
        create(rb).await().also { logger.info("Registration completed") }
    }

    fun all() = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.session.value as? Session.SignedIn ?: error("You must be signed in to query businesses"),
            data = ""
        )
        all(rb).await()
    }
}