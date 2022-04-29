package pimonitor.core.portfolio

import bitframe.core.RequestBody
import later.Later
import kotlin.js.JsName

interface PortfolioServiceCore {
    @JsName("_ignore_load")
    fun load(rb: RequestBody.Authorized<PortfolioFilter>): Later<MonitoredBusinessPortfolio>
}