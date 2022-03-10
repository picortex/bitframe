package akkounts.sage

import akkounts.accounts.AccountsService
import akkounts.accounts.UnimplementedAccountsService
import akkounts.bills.BillsService
import akkounts.bills.UnimplementedBillService
import akkounts.expenses.ExpensesService
import akkounts.expenses.UnimplementedExpensesService
import akkounts.invoices.InvoiceService
import akkounts.invoices.UnimplementedInvoiceService
import akkounts.provider.AccountingPackager
import akkounts.provider.Owner
import akkounts.provider.Vendor
import akkounts.reports.services.ReportsService
import akkounts.sage.reports.SageOneZAReportsService
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads

class SageOneZAPackager @JvmOverloads constructor(
    val service: SageOneZAService,
    val owner: Owner,
    val credentials: Credentials,
    override val accounts: AccountsService = UnimplementedAccountsService(VENDOR),
    override val bills: BillsService = UnimplementedBillService(VENDOR),
    override val expenses: ExpensesService = UnimplementedExpensesService(VENDOR),
    override val invoices: InvoiceService = UnimplementedInvoiceService(VENDOR),
    override val reports: ReportsService = SageOneZAReportsService(owner, credentials, service.environment),
) : AccountingPackager {
    companion object {
        @JvmField
        val VENDOR = Vendor("Sage Business Cloud - Accounting", "Sage Group PLC")
    }

    enum class Environment(val path: String) {
        PROD("https://accounting.sageone.co.za/api/2.00"),
        TEST("https://resellers.accounting.sageone.co.za/api/2.0.0")
    }
}