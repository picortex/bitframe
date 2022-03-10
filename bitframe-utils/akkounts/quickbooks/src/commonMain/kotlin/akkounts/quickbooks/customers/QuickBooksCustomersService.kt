package akkounts.quickbooks.customers

import akkounts.QuickBooksCompany
import akkounts.QuickBooksTokenStorage
import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.Service
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import later.await
import later.later
import kotlin.jvm.JvmOverloads

class QuickBooksCustomersService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {
    private val parser = CustomerParser

    fun search(company: QuickBooksCompany, displayName: String) = scope.later {
        val res = query(company, """select * from Customer Where DisplayName='$displayName'""")
        parser.decodeManyFromJsonResponse(res)
    }

    fun create(company: QuickBooksCompany, params: QuickBooksCustomerParams) = scope.later {
        val args = mapOf(
            "DisplayName" to params.displayName,
            "GivenName" to params.givenName
        )
        val res = post(company, Service.Endpoint.Customer, args)
        parser.decodeSingleFromJsonResponse(res)
    }

    fun all(company: QuickBooksCompany) = scope.later {
        val resp = query(company, """select * from Customer""")
        parser.decodeManyFromJsonResponse(resp)
    }

    fun getOrCreate(company: QuickBooksCompany, params: QuickBooksCustomerParams) = scope.later {
        search(company, displayName = params.displayName).await().firstOrNull() ?: create(company, params).await()
    }
}