@file:JsExport

package pimonitor.core.accounting

import kotlin.js.JsExport
import kotlin.jvm.JvmField

object FINANCIAL_REPORTS {
    @JvmField
    val BALANCE_SHEET = "BalanceSheet"

    @JvmField
    val INCOME_STATEMENT = "IncomeStatement"

    @JvmField
    val CASH_FLOW = "CashFlow"
}