package pimonitor.core.picortex

import kotlinx.collections.interoperable.mutableListOf
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper
import presenters.charts.BarChart
import kotlinx.collections.interoperable.List as IList

internal class PiCortexDashboardParser(val mapper: Mapper) {
    constructor(json: Json = PiCortexDashboardProviderConfig.DEFAULT_JSON) : this(Mapper(json))

    companion object {
        const val NUMBER_OF_CLIENT = "Number of Clients"
        const val NUMBER_OF_EMPLOYEES = "Number of Employees"
        const val NUMBER_OF_JOBS = "Jobs this Month"
        const val NUMBER_OF_QUOTATIONS = "Quotations this Month"
        const val NUMBER_OF_INVOICES = "Revenue this Month"

        // values
        const val EXPENSES_VALUE = "Expenses this Month"
        const val OUTSTANDING_PAYMENTS_TO_SUPPLIERS = "Outstanding Payments To Suppliers"
        const val OUTSTANDING_PAYMENTS_FROM_CUSTOMERS = "Outstanding Payments From Customers"

        //charts
        const val PRODUCTS_SOLD = "Products Sold"
        const val JOBS_PER_CLIENT = "Jobs per Client"
        const val HOURS_WORKED_PER_EMPLOYEE = "Hours worked per employee"
    }

    private fun entries(json: String): List<Map<String, Any>> {
        val map = mapper.decodeFromString(json)
        return map["reports"] as List<Map<String, Any>>
    }

    private val Map<String, Any>.config get() = this["config"] as Map<String, Any>

    private fun searchEntryWith(json: String, description: String): Map<String, Any> = entries(json).firstOrNull {
        it.config["description"] == description
    } ?: mapOf()

    private fun parseSingleValueOf(description: String, from: String): String {
        val entry = searchEntryWith(from, description = description)
        return entry["singleValueString"].toString()
    }

    fun parseNumberOfJobs(json: String): String = parseSingleValueOf(
        description = NUMBER_OF_JOBS,
        from = json
    )

    fun parseNumberOfClients(json: String): String = parseSingleValueOf(
        description = NUMBER_OF_CLIENT,
        from = json
    )

    fun parseNumberOfEmployees(json: String): String = parseSingleValueOf(
        description = NUMBER_OF_EMPLOYEES,
        from = json
    )

    fun parseExpenses(json: String) = parseSingleValueOf(
        description = EXPENSES_VALUE,
        from = json
    )

    fun parseNumberOfQuotations(json: String) = parseSingleValueOf(
        description = NUMBER_OF_QUOTATIONS,
        from = json
    )

    fun parseNumberOfInvoices(json: String) = ""

    fun parseOutstandingPaymentsToSuppliers(json: String) = parseSingleValueOf(
        description = OUTSTANDING_PAYMENTS_TO_SUPPLIERS,
        from = json
    )

    fun parseOutstandingPaymentsFromCustomers(json: String) = parseSingleValueOf(
        description = OUTSTANDING_PAYMENTS_FROM_CUSTOMERS,
        from = json
    )

    fun parseBarChartEntriesOf(type: String, from: String): IList<BarChart.Entry<Double>> {
        val entries = searchEntryWith(from, type)
        val data = entries["tabularData"] as Map<String, Map<String, Any>>

        val entry = mutableListOf<BarChart.Entry<Double>>()
        for ((key, value) in data) {
            entry.add(BarChart.Entry(key, value["All"]?.toString()?.toDouble() ?: 0.0))
        }
        return entry
    }

    fun parseProductsSoldChart(json: String) = BarChart(
        title = "Products sold",
        description = "Products sold this month",
        entries = parseBarChartEntriesOf(
            type = PRODUCTS_SOLD,
            from = json
        )
    )

    fun parseJobsPerClientChart(json: String) = BarChart(
        title = "Jobs per client",
        description = "Jobs fulfilled in the last month",
        entries = parseBarChartEntriesOf(
            type = JOBS_PER_CLIENT,
            from = json
        )
    )

    fun parseHoursPerEmployee(json: String) = BarChart(
        title = "Hours per employee",
        description = "Hours worked in the last month",
        entries = parseBarChartEntriesOf(
            type = HOURS_WORKED_PER_EMPLOYEE,
            from = json
        )
    )

    fun parseTechnicalDashboard(json: String): OperationalDashboard {
        val board = OperationalDashboard()
        return board.copy(
            clients = board.clients.copy(value = parseNumberOfClients(json)),
            employees = board.employees.copy(value = parseNumberOfEmployees(json)),
            jobs = board.jobs.copy(value = parseNumberOfJobs(json)),
            expenses = board.expenses.copy(value = parseExpenses(json)),
            quotations = board.quotations.copy(value = parseNumberOfQuotations(json)),
            invoices = board.invoices.copy(value = parseNumberOfInvoices(json)),
            paymentsToSuppliers = board.paymentsToSuppliers.copy(value = parseOutstandingPaymentsToSuppliers(json)),
            paymentsFromCustomers = board.paymentsFromCustomers.copy(value = parseOutstandingPaymentsFromCustomers(json)),
            sales = parseProductsSoldChart(json),
            jobsPerClient = parseJobsPerClientChart(json),
            hoursPerEmployee = parseHoursPerEmployee(json)
        )
    }
}