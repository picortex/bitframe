package akkounts.quickbooks.taxes

import akkounts.QuickBooksCompany
import akkounts.QuickBooksTokenStorage
import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.Service
import payments.requests.*
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import later.Later
import later.await
import later.later
import kotlin.jvm.JvmOverloads

class QuickBooksTaxingServices @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {
    val agencies = QuickBooksTaxAgenciesService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    val rates = QuickBooksTaxRatesService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    val codes = QuickBooksTaxCodeService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    fun create(company: QuickBooksCompany, tax: Tax): Later<Tax> = scope.later {
        getOrCreateRaw(company, tax)
        tax
    }

    fun getOrCreateRaw(company: QuickBooksCompany, tax: Tax): Later<Map<String, *>> = scope.later {
        codes.findRaw(company, tax).await() ?: rates.createRaw(company, tax).await()
    }
}