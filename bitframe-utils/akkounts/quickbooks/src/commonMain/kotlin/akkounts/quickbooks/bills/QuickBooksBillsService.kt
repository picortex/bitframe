package akkounts.quickbooks.bills

import akkounts.QuickBooksCompany
import akkounts.QuickBooksTokenStorage
import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.Service
import akkounts.quickbooks.accounts.QuickBooksAccountsService
import akkounts.quickbooks.vendors.QuickBooksVendorsService
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.mapper.Mapper
import later.await
import later.later
import payments.requests.Bill
import kotlin.jvm.JvmOverloads

class QuickBooksBillsService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {

    private val decoder = BillDecoder

    val vendors = QuickBooksVendorsService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    val accounts = QuickBooksAccountsService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    fun create(company: QuickBooksCompany, bill: Bill) = scope.later {
        val vendor = vendors.getOrCreate(company, bill.header.sender.toQuickBooksVendorParams()).await()
        val account = accounts.getOrCreate(company, DefaultBillsAccountParams(bill.header.currency)).await()
        val params = bill.billParams(bill, vendor, account)
        try {
            val res = post(company, Service.Endpoint.Bill, params)
            decoder.decodeSingleResponse(bill, company, vendor, res)
        } catch (cause: Throwable) {
            throw RuntimeException("Failed to create an invoice", cause)
        }
    }
}