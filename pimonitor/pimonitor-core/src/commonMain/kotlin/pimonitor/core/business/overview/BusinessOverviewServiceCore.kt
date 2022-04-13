@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.overview

import bitframe.core.RequestBody
import later.Later
import pimonitor.core.business.utils.info.LoadInfoParsedParams
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface BusinessOverviewServiceCore {
    @JsName("_ignore_load")
    fun load(rb: RequestBody.Authorized<LoadInfoParsedParams>): Later<MonitoredBusinessOverview>
}