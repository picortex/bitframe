@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.overview

import bitframe.client.ServiceConfig
import bitframe.client.logger
import later.Later
import later.later
import pimonitor.core.business.overview.BusinessOverviewServiceCore
import pimonitor.core.business.overview.MonitoredBusinessOverview
import pimonitor.core.business.utils.info.LoadInfoRawParams
import kotlin.js.JsExport

abstract class BusinessOverviewService(private val config: ServiceConfig) : BusinessOverviewServiceCore {
    protected val logger by config.logger(withSessionInfo = true)
    fun load(params: LoadInfoRawParams): Later<MonitoredBusinessOverview> = config.scope.later {
        logger.info("Loading business overview's please wait...")
        TODO()
    }
}