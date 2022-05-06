package pimonitor.core.businesses

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.changes.toString
import presenters.table.builders.TableBuilder

fun TableBuilder<MonitoredBusinessSummary>.MonitoredBusinessColumns() {
    column("Name") { it.data.name }
    column("Operations") { it.data.operationalBoard }
    column("Accounting") { it.data.financialBoard }
    column("Revenue") { it.data.revenue.toString() }
    column("Expenses") { it.data.expenses.toString() }
    column("GP") { it.data.grossProfit.toString() }
    // column("Velocity") { it.data.velocity.toString() }
    // column("NCF") { it.data.netCashFlow.toString() }
    // column("V/day") { it.data.velocity.toString() }
}