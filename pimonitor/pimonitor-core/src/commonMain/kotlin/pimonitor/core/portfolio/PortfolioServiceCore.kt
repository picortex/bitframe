package pimonitor.core.portfolio

import bitframe.core.RequestBody
import bitframe.core.ServiceConfig
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import later.Later
import later.later
import pimonitor.core.businesses.BusinessesServiceCore
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.cards.ValueCard
import presenters.fields.BooleanInputField
import kotlin.js.JsName
import kotlin.random.Random

interface PortfolioServiceCore {
    val config: ServiceConfig
    val scope get() = config.scope

    @JsName("_ignore_load")
    fun load(rb: RequestBody.Authorized<PortfolioFilter>): Later<PortfolioData>
}