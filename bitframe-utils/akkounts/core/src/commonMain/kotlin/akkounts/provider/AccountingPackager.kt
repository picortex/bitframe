package akkounts.provider

import akkounts.accounts.AccountsService
import akkounts.bills.BillsService
import akkounts.expenses.ExpensesService
import akkounts.invoices.InvoiceService
import akkounts.reports.services.ReportsService

interface AccountingPackager {
    val accounts: AccountsService
    val bills: BillsService
    val expenses: ExpensesService
    val invoices: InvoiceService
    val reports: ReportsService
}