package akkounts.quickbooks

import akkounts.*
import akkounts.provider.AccountingPackager
import akkounts.provider.Vendor
import akkounts.quickbooks.accounts.QuickBooksAccountsService
import akkounts.quickbooks.bills.QuickBooksBillsService
import akkounts.quickbooks.vendors.QuickBooksVendorsService
import akkounts.quickbooks.invoices.QuickBooksInvoiceService
import akkounts.quickbooks.items.QuickBooksItemsService
import akkounts.quickbooks.payments.QuickBooksPaymentService
import akkounts.quickbooks.purchase.QuickBooksPurchaseService
import akkounts.quickbooks.reports.QuickBooksReportsService
import akkounts.quickbooks.taxes.QuickBooksTaxingServices
import akkounts.regulation.QueryRegulator
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads

class QuickBooksService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: Environment = Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {

    companion object {
        @JvmField
        val VENDOR = Vendor("QuickBooksOnline", "Intuit Inc")
    }

    enum class Environment(val url: String) {
        TEST("https://sandbox-quickbooks.api.intuit.com"),
        PROD("https://quickbooks.api.intuit.com")
    }

    data class Tokens(val access: String, val refresh: String)

    val accounts = QuickBooksAccountsService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    val customers = QuickBooksVendorsService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    val reports = QuickBooksReportsService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    val invoices = QuickBooksInvoiceService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    val items = QuickBooksItemsService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    val tax = QuickBooksTaxingServices(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    val purchases = QuickBooksPurchaseService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    val payments = QuickBooksPaymentService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    val bills = QuickBooksBillsService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    @JvmOverloads
    fun offeredTo(
        company: QuickBooksCompany,
        regulator: QueryRegulator = QueryRegulator.FORGIVING
    ): AccountingPackager = QuickBooksPackager(this, company, regulator)
}