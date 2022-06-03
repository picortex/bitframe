package akkounts

import akkounts.accounts.AccountsService
import akkounts.accounts.UnimplementedAccountsService
import akkounts.bills.BillsService
import akkounts.bills.UnimplementedBillService
import akkounts.expenses.ExpensesService
import akkounts.expenses.UnimplementedExpensesService
import akkounts.invoices.InvoiceService
import akkounts.invoices.UnimplementedInvoiceService
import akkounts.provider.AccountingPackager
import akkounts.provider.Vendor
import akkounts.reports.services.ReportsService

class MockPackager(
    val consumer: MockConsumer,
) : AccountingPackager {
    override val accounts: AccountsService = UnimplementedAccountsService(VENDOR)
    override val bills: BillsService = UnimplementedBillService(VENDOR)
    override val expenses: ExpensesService = UnimplementedExpensesService(VENDOR)
    override val invoices: InvoiceService = UnimplementedInvoiceService(VENDOR)
    override val reports: ReportsService = MockReportsService(consumer)

    companion object {
        internal val VENDOR = Vendor(
            product = "Akkounts Mock Packager",
            company = "PiCortex"
        )
    }
}