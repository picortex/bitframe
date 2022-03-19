@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package pimonitor.core.businesses

import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.incomestatement.IncomeStatement
import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.*
import pimonitor.core.businesses.results.AvailableReportsResults
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.invites.InfoResults
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
    fun operationalDashboard(rb: RequestBody.Authorized<String>): Later<InfoResults<OperationalDashboard>>

    /**
     * @param rb - [RequestBody.Authorized]<BusinessId> where BusinessId is a string
     */
    @JvmSynthetic
    @JsName("_ignore_incomeStatement")
    fun incomeStatement(rb: RequestBody.Authorized<String>): Later<InfoResults<IncomeStatement>>

    /**
     * @param rb - [RequestBody.Authorized]<BusinessId> where BusinessId is a string
     */
    @JvmSynthetic
    @JsName("_ignore_balanceSheet")
    fun balanceSheet(rb: RequestBody.Authorized<String>): Later<InfoResults<BalanceSheet>>

    /**
     * @param rb - [RequestBody.Authorized]<BusinessId> where BusinessId is a string
     */
    @JvmSynthetic
    @JsName("_ignore_load")
    fun load(rb: RequestBody.Authorized<String>): Later<MonitoredBusinessBasicInfo>

    /**
     * @param rb - [RequestBody.Authorized]<BusinessId> where BusinessId is a string
     */
    @JvmSynthetic
    @JsName("_ignore_availableReports")
    fun availableReports(rb: RequestBody.Authorized<String>): Later<AvailableReportsResults>
}