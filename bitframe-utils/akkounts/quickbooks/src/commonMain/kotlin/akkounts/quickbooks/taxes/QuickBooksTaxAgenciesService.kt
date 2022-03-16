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
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList

class QuickBooksTaxAgenciesService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {
    val parser = QuickBooksTaxAgencyParser()
    fun create(company: QuickBooksCompany, agency: TaxAgency) = scope.later {
        createRaw(company, agency).await()
        agency
    }

    fun createRaw(company: QuickBooksCompany, agency: TaxAgency) = scope.later {
        post(
            company = company,
            endpoint = Service.Endpoint.Tax.Agency,
            params = mapOf("DisplayName" to agency.name)
        )["TaxAgency"] as Map<String, *>
    }

    fun getOrCreateRaw(company: QuickBooksCompany, agency: TaxAgency) = scope.later {
        findRaw(company, agency).await() ?: createRaw(company, agency).await()
    }

    fun findRaw(company: QuickBooksCompany, agency: TaxAgency): Later<Map<String, *>?> = scope.later {
        val list = allRaw(company).await()
        list.firstOrNull { it["DisplayName"] == agency.name }
    }

    fun allRaw(company: QuickBooksCompany): Later<List<Map<String, *>>> = scope.later {
        val map = query(company, "Select * from TaxAgency")
        parser.rawListMap(map).toInteroperableList()
    }

    fun all(company: QuickBooksCompany): Later<List<TaxAgency>> = scope.later {
        val list = allRaw(company).await()
        parser.parseMany(list).toInteroperableList()
    }
}