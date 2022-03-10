package akkounts.quickbooks.invoices

import akkounts.QuickBooksCompany
import akkounts.QuickBooksTokenStorage
import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.Service
import akkounts.quickbooks.customers.QuickBooksCustomersService
import akkounts.quickbooks.items.QuickBooksItemsService
import akkounts.quickbooks.payments.QuickBooksPaymentService
import akkounts.quickbooks.taxes.QuickBooksTaxingServices
import payments.requests.*
import io.ktor.client.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import later.Later
import later.await
import later.later
import kotlin.jvm.JvmOverloads

class QuickBooksInvoiceService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {

    private val decoder = InvoiceDecoder

    private val customers = QuickBooksCustomersService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    private val items = QuickBooksItemsService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    private val taxes = QuickBooksTaxingServices(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    val payments = QuickBooksPaymentService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    fun create(company: QuickBooksCompany, invoice: Invoice) = scope.later {
        val customer = customers.getOrCreate(company, invoice.header.receiver.toQuickBooksCustomerParams()).await()
        val lineItems = items.create(company, invoice.toQuickBooksItemParams()).await()
        val tax = invoice.body.taxRates.keys.first()
        val rawTax = taxes.getOrCreateRaw(company, tax).await()
        val taxCodeRef = rawTax["Id"] ?: rawTax["TaxCodeId"] ?: error("Failed to create ${tax.name}")
        val params = invoice.invoiceParams(customer, lineItems, taxCodeRef.toString())
        try {
            val res = post(company, Service.Endpoint.Invoice, params)
            decoder.decodeSingleResponse(invoice, company, customer, res)
        } catch (cause: Throwable) {
            throw RuntimeException("Failed to create an invoice", cause)
        }
    }

    fun all(company: QuickBooksCompany): Later<List<Invoice>> = scope.later {
        val response = query(company, "Select * from Invoice")
        decoder.decodeManyResponse(company, response).toInteroperableList()
    }
}