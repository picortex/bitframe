package pimonitor.core.business.operations

import bitframe.core.RequestBody
import later.Later
import pimonitor.core.business.params.LoadReportParams
import pimonitor.core.dashboards.OperationalDifferenceBoard
import pimonitor.core.invites.InfoResults
import kotlin.js.JsName

interface BusinessOperationsServiceCore {
    @JsName("_ignore_dashboard")
    fun dashboard(rb: RequestBody.Authorized<LoadReportParams>): Later<InfoResults<OperationalDifferenceBoard>>
}