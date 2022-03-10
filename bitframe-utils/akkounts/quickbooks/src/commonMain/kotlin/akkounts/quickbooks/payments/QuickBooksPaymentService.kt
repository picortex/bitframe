package akkounts.quickbooks.payments

import akkounts.QuickBooksCompany
import akkounts.QuickBooksTokenStorage
import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.Service
import akkounts.quickbooks.accounts.QuickBooksAccountsService
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.mapper.Mapper
import later.await
import later.later
import kotlin.jvm.JvmOverloads

class QuickBooksPaymentService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {

    private val encoder = PaymentServiceEncoder

    val accounts = QuickBooksAccountsService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    fun createRaw(company: QuickBooksCompany, params: PaymentParams) = scope.later {
        val account = accounts.getOrCreate(company, DefaultCashAccountForPayments(params.amount.currency)).await()
        post(company, Service.Endpoint.Payment, encoder.encode(params, account))
    }
}