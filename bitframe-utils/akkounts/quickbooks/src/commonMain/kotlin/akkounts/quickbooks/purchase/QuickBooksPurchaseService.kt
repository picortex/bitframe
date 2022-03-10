package akkounts.quickbooks.purchase

import akkounts.QuickBooksCompany
import akkounts.QuickBooksTokenStorage
import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.Service
import akkounts.quickbooks.accounts.QuickBooksAccountsService
import akkounts.payments.PaymentType
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import later.await
import later.later
import kotlin.jvm.JvmOverloads

class QuickBooksPurchaseService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {
    val encoder = PurchaseEncoder

    val account = QuickBooksAccountsService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    @OptIn(ExperimentalStdlibApi::class)
    fun createRaw(company: QuickBooksCompany, params: PurchaseParams) = scope.later {
        try {
            val (assetAccount, expenseAccount) = when (params.payment) {
                PaymentType.Cash -> account.getOrCreate(
                    company, listOf(
                        DefaultCashAccountForPurchases(params.currency),
                        DefaultPurchaseExpenseAccountParam(params.currency)
                    )
                )
                PaymentType.CreditCard -> error("Purchase with credit card will be added soon")
                PaymentType.Check -> error("Purchase with Check will be added soon")
            }.await()
            val args = encoder.encode(params, assetAccount, expenseAccount)
            post(company, Service.Endpoint.Purchase, args)
        } catch (cause: Throwable) {
            throw IllegalArgumentException("Failed to create a purchase on quickbooks", cause)
        }
    }
}