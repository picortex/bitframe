@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package pimonitor.core.businesses

import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.*
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmSynthetic

@JsExport
interface BusinessesServiceCore {
    @JsName("_ignore_create")
    @JvmSynthetic
    fun create(rb: RequestBody.Authorized<CreateMonitoredBusinessParams>): Later<CreateMonitoredBusinessResult>

    @JsName("_ignore_all")
    @JvmSynthetic
    fun all(rb: RequestBody.Authorized<BusinessFilter>): Later<List<MonitoredBusinessSummary>>

    @JsName("_ignore_delete")
    @JvmSynthetic
    fun delete(rb: RequestBody.Authorized<Array<out String>>): Later<List<MonitoredBusinessBasicInfo>>

    /**
     * @param rb - [RequestBody.Authorized]<BusinessId> where BusinessId is a string
     */
    @JvmSynthetic
    @JsName("_ignore_load")
    fun load(rb: RequestBody.Authorized<String>): Later<MonitoredBusinessBasicInfo>
}