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

class QuickBooksTaxCodeService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {
    val parser = QuickBooksTaxCodeParser()

    fun findRaw(company: QuickBooksCompany, tax: Tax): Later<Map<String, *>?> = scope.later {
        val list = allRaw(company).await()
        list.firstOrNull { it["Name"] == "${tax.agency.name} - ${tax.rate}% ${tax.name}" }
    }

    fun allRaw(company: QuickBooksCompany): Later<List<Map<String, *>>> = scope.later {
        val map = query(company, "Select * from TaxCode")
        parser.parseRaw(map)
    }
}