@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package pimonitor.core.businesses

import akkounts.reports.incomestatement.IncomeStatement
import bitframe.core.RequestBody
import bitframe.core.ServiceConfig
import events.Event
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.*
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.invites.Invite
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
    @JsName("_ignore_operationalDashboard")
    fun operationalDashboard(rb: RequestBody.Authorized<String>): Later<OperationalDashboard?>

    /**
     * @param rb - [RequestBody.Authorized]<BusinessId> where BusinessId is a string
     */
    @JvmSynthetic
    @JsName("_ignore_incomeStatement")
    fun incomeStatement(rb: RequestBody.Authorized<String>): Later<IncomeStatement?>
}