@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.client.logger
import bitframe.core.RequestBody
import later.Later
import later.await
import later.later
import pimonitor.core.business.params.LoadReportRawParams
import pimonitor.core.business.params.toValidatedParams
import pimonitor.core.businesses.BusinessFilter
import pimonitor.core.businesses.BusinessesServiceCore
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.toValidatedCreateBusinessParams
import kotlin.js.JsExport

abstract class BusinessesService(
    private val config: ServiceConfig
) : BusinessesServiceCore {

    val logger by config.logger(withSessionInfo = true)

    fun create(params: CreateMonitoredBusinessRawParams) = config.scope.later {
        logger.info("Registering business ${params.businessName}")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("create a business"),
            data = params.toValidatedCreateBusinessParams()
        )
        val result = create(rb).await()
        config.bus.dispatch(BusinessAddedEvent(data = result, spaceId = rb.session.space.uid))
        logger.info("Registration completed")
        result
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

    fun operationalDashboard(params: LoadReportRawParams) = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load operational dashboard info for business with id=${params.businessId}"),
            data = params.toValidatedParams()
        )
        operationalDashboard(rb).await()
    }

    fun incomeStatement(businessId: String) = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load income statement"),
            data = businessId
        )
        incomeStatement(rb).await()
    }

    fun balanceSheet(businessId: String) = config.scope.later {
        logger.info("Loading balance sheet for business(uid=$businessId)")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load income statement"),
            data = businessId
        )
        balanceSheet(rb).await().also { logger.info("Loaded balance sheet: $it") }
    }

    fun load(businessId: String): Later<MonitoredBusinessBasicInfo> = config.scope.later {
        logger.info("Loading business(uid=$businessId)")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load business"),
            data = businessId
        )
        load(rb).await().also { logger.info("Loaded business: $it") }
    }

    fun availableReports(businessId: String) = config.scope.later {
        logger.info("fetching available reports from business(uid=$businessId)")
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("load available reports"),
            data = businessId
        )
        availableReports(rb).await().also { logger.info("Reports found"); }
    }
}