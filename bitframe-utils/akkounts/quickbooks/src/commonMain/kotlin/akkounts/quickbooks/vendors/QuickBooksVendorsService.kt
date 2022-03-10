package akkounts.quickbooks.vendors

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

class QuickBooksVendorsService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {

    private val parser = VendorParser

    fun search(company: QuickBooksCompany, displayName: String) = scope.later {
        val json = query(company, """select * from vendor Where DisplayName='$displayName'""")
        parser.decodeManyFromJsonResponse(json)
    }

    fun create(company: QuickBooksCompany, params: VendorParams) = scope.later {
        val args = mapOf(
            "DisplayName" to params.displayName,
            "GivenName" to params.givenName
        )
        val res = post(company, Service.Endpoint.Vendor, args)
        parser.decodeSingleFromJsonResponse(res)
    }

    fun all(company: QuickBooksCompany) = scope.later {
        val json = query(company, """select * from vendor""")
        parser.decodeManyFromJsonResponse(json)
    }

    fun getOrCreate(company: QuickBooksCompany, params: VendorParams) = scope.later {
        search(company, displayName = params.displayName).await().firstOrNull() ?: create(company, params).await()
    }
}