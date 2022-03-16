package akkounts.quickbooks.reports

import akkounts.QuickBooksCompany
import akkounts.quickbooks.QuickBooksService
import akkounts.reports.services.ReportsService
import kotlinx.datetime.LocalDate

class QuickBooksPackageReportsService(
    private val service: QuickBooksService,
    private val company: QuickBooksCompany
) : ReportsService {
    override fun balanceSheet(at: LocalDate) = service.reports.balanceSheet(company, at)

    override fun incomeStatement(
        start: LocalDate,
        end: LocalDate
    ) = service.reports.incomeStatement(company, start, end)

    override fun cashFlow(
        start: LocalDate,
        end: LocalDate
    ) = service.reports.cashFlow(company, start, end)
}