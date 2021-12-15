package unit

import expect.expect
import pimonitor.integrations.PiCortexDashboardParser
import kotlin.test.Test

class PiCortexDashboardParserTest {
    private val json = PiCortexJsonResponse
    private val parser = PiCortexDashboardParser()

    @Test
    fun should_parse_number_of_clients() {
        val clients = parser.parseNumberOfClients(json)
        expect(clients).toBe("23")
    }

    @Test
    fun should_parse_number_of_employees() {
        val employees = parser.parseNumberOfEmployees(json)
        expect(employees).toBe("16")
    }

    @Test
    fun should_parse_number_of_jobs() {
        val jobs = parser.parseNumberOfJobs(json)
        expect(jobs).toBe("0")
    }

    @Test
    fun should_parse_expenses() {
        val expenses = parser.parseExpenses(json)
        expect(expenses).toBe("R 1,365.55")
    }

    @Test
    fun should_parse_number_of_quotation() {
        val quotations = parser.parseNumberOfQuotations(json)
        expect(quotations).toBe("0")
    }

    @Test
    fun should_parse_number_of_invoices() {
        val invoices = parser.parseNumberOfInvoices(json)
        expect(invoices).toBe("")
    }

    @Test
    fun should_parse_outstanding_payments_to_suppliers() {
        val payments = parser.parseOutstandingPaymentsToSuppliers(json)
        expect(payments).toBe("R 251,555.55")
    }

    @Test
    fun should_parse_outstanding_payments_from_customers() {
        val payments = parser.parseOutstandingPaymentsFromCustomers(json)
        expect(payments).toBe("R 84,836.94")
    }

    @Test
    fun should_parse_products_sold_chart() {
        val chart = parser.parseProductsSoldChart(json)
        show(chart)
        expect(chart).toBeNonNull()
    }

    @Test
    fun should_parse_jobs_per_client_chart() {
        val chart = parser.parseJobsPerClientChart(json)
        show(chart)
        expect(chart).toBeNonNull()
    }

    @Test
    fun should_parse_hours_per_employee() {
        val chart = parser. parseHoursPerEmployee(json)
        show(chart)
        expect(chart).toBeNonNull()
    }

    @Test
    fun should_do_this_and_that() {
        /**
         * const business = state.business
         *
         * const oData = business.operationData = {
         *   cards: [],
         *   charts: []
         * }
         *
         * const overview = business.overview = {
         *  info: {},
         *  cards: [],
         *  charts: []
         * }
         */
    }
}