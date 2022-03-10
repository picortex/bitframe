package akkounts.quickbooks

import akkounts.QuickBooksCompany
import akkounts.QuickBooksTokenStorage
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.mapper.Mapper
import later.Later
import later.await
import later.later

interface Service {
    val clientId: String
    val clientSecret: String
    val redirectUrl: String
    val storage: QuickBooksTokenStorage
    val environment: QuickBooksService.Environment
    val scope: CoroutineScope
    val client: HttpClient
    val version: String

    sealed class Endpoint(val value: String) {
        object Account : Endpoint("account")
        object Bill : Endpoint("bill")
        object Customer : Endpoint("customer")
        object Invoice : Endpoint("invoice")
        object Item : Endpoint("item")
        object Payment : Endpoint("payment")
        object Purchase : Endpoint("purchase")
        sealed class Tax(value: String) : Endpoint(value) {
            object Agency : Tax("taxagency")
            object Code : Tax("taxservice/taxcode")
        }

        object Vendor : Endpoint("vendor")
    }

    @OptIn(InternalAPI::class)
    fun refreshTokens(company: QuickBooksCompany): Later<QuickBooksCompany> = scope.later {
        try {
            val res = client.post("https://oauth.platform.intuit.com/oauth2/v1/tokens/bearer") {
                header(HttpHeaders.Accept, ContentType.Application.Json)
                header(HttpHeaders.Authorization, "Basic ${"$clientId:$clientSecret".encodeBase64()}")
                val formContent = Parameters.build {
                    append("grant_type", "refresh_token")
                    append("refresh_token", company.refreshToken)
                }
                setBody(FormDataContent(formContent))
            }
            val map = Mapper.decodeFromString(res.bodyAsText())
            storage.updateOrCreate(
                company.copy(
                    refreshToken = map["refresh_token"].toString(),
                    accessToken = map["access_token"].toString()
                )
            ).await()
        } catch (cause: Throwable) {
            throw Exception("Failed to refresh a new token. Check to see if the current refresh token is valid", cause)
        }
    }

    fun JsonContent(map: Map<String, Any?>) = TextContent(
        text = Mapper.encodeToString(map),
        contentType = ContentType.Application.Json
    )

    suspend fun post(
        company: QuickBooksCompany,
        endpoint: Endpoint,
        params: Map<String, Any?>
    ): Map<String, Any?> {
        val res = client.post("""${baseUrl(company)}/${endpoint.value}?minorversion=59""") {
            header(HttpHeaders.Accept, ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer ${company.accessToken}")
            setBody(JsonContent(params))
        }
        return Mapper.decodeFromString(res.bodyAsText())
    }

    fun baseUrl(company: QuickBooksCompany) = """${environment.url}/$version/company/${company.realmId}"""

    suspend fun query(company: QuickBooksCompany, query: String): Map<String, *> {
        val url = """${baseUrl(company)}/query?query=$query&minorversion=59"""
        val res = client.get(url) {
            header(HttpHeaders.Accept, ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer ${company.accessToken}")
        }
        return Mapper.decodeFromString(res.bodyAsText())
    }
}