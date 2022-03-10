package akkounts.quickbooks

import akkounts.QuickBooksCompany
import akkounts.accounts.AccountsService
import akkounts.bills.BillsService
import akkounts.expenses.ExpensesService
import akkounts.invoices.InvoiceService
import akkounts.provider.AccountingPackager
import akkounts.quickbooks.accounts.QuickBooksPackageAccountsService
import akkounts.quickbooks.bills.QuickBooksPackagerBillsServices
import akkounts.quickbooks.expenses.QuickBooksPackageExpensesService
import akkounts.quickbooks.invoices.QuickBooksPackagerInvoiceService
import akkounts.quickbooks.reports.QuickBooksPackageReportsService
import akkounts.regulation.QueryRegulator
import akkounts.reports.services.ReportsService
import kotlin.jvm.JvmOverloads

class QuickBooksPackager @JvmOverloads constructor(
    val service: QuickBooksService,
    val company: QuickBooksCompany,
    val regulator: QueryRegulator = QueryRegulator.FORGIVING,
    override val accounts: AccountsService = QuickBooksPackageAccountsService(service, company),
    override val bills: BillsService = QuickBooksPackagerBillsServices(service, company),
    override val expenses: ExpensesService = QuickBooksPackageExpensesService(service, company),
    override val invoices: InvoiceService = QuickBooksPackagerInvoiceService(service, company),
    override val reports: ReportsService = QuickBooksPackageReportsService(service, company),
) : AccountingPackager