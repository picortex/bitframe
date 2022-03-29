package pimonitor.core.business.operations

import bitframe.core.RequestBody
import later.Later
import pimonitor.core.business.utils.info.LoadInfoParams
import pimonitor.core.dashboards.OperationalDifferenceBoard
import pimonitor.core.invites.InfoResults
import kotlin.js.JsName

interface BusinessOperationsServiceCore {
    @JsName("_ignore_dashboard")
    fun dashboard(rb: RequestBody.Authorized<LoadInfoParams>): Later<InfoResults<OperationalDifferenceBoard>>
}