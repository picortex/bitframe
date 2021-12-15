@file:JsExport

package pimonitor.integrations

import presenters.charts.BarChart
import kotlin.js.JsExport

data class OperationalDashboard(
    val clients: DashboardEntry = DashboardEntry("Clients"),
    val employees: DashboardEntry = DashboardEntry("Employees"),
    val jobs: DashboardEntry = DashboardEntry("Jobs"),
    val expenses: DashboardEntry = DashboardEntry("Expenses"),
    val quotations: DashboardEntry = DashboardEntry("Quotation (this month)"),
    val invoices: DashboardEntry = DashboardEntry("Invoices (this month)"),
    val paymentsToSuppliers: DashboardEntry = DashboardEntry("Outstanding payments to suppliers"),
    val paymentsFromCustomers: DashboardEntry = DashboardEntry("Outstanding payments from customers"),
    val sales: BarChart<Double> = BarChart(
        title = "Products Sold",
        description = "Products sold in the last month"
    ),
    val jobsPerClient: BarChart<Double> = BarChart(
        title = "Jobs per client",
        description = "Jobs full field in the last month"
    ),
    val hoursPerEmployee: BarChart<Double> = BarChart(
        title = "House per employee",
        description = "Hours worked in the last month"
    )
)