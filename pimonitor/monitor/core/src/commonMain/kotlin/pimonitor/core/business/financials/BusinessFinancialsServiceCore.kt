package pimonitor.core.business.financials

import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.incomestatement.IncomeStatement
import bitframe.core.RequestBody
import later.Later
import pimonitor.core.businesses.results.AvailableReportsResults
import pimonitor.core.invites.InfoResults
import kotlin.js.JsName
import kotlin.jvm.JvmSynthetic

interface BusinessFinancialsServiceCore {
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
    @JsName("_ignore_availableReports")
    fun availableReports(rb: RequestBody.Authorized<String>): Later<AvailableReportsResults>
}