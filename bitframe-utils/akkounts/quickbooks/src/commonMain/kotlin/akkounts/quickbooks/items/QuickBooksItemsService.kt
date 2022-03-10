package akkounts.quickbooks.items

import akkounts.QuickBooksCompany
import akkounts.QuickBooksTokenStorage
import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.Service
import akkounts.quickbooks.accounts.InventoryItemExpenseAccountParam
import akkounts.quickbooks.accounts.InventoryItemIncomeAccountParams
import akkounts.quickbooks.accounts.QuickBooksAccountsService
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import later.await
import later.later
import kotlin.jvm.JvmOverloads

class QuickBooksItemsService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {
    private val accounts = QuickBooksAccountsService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    fun search(company: QuickBooksCompany, name: String?) = scope.later {
        val query = "select * from Item " + if (name == null) "maxresults 4" else "Where name='$name'"
        val url = """${environment.url}/$version/company/${company.realmId}/query?query=$query&minorversion=57"""
        val res = client.get(url) {
            header(HttpHeaders.Accept, ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer ${company.accessToken}")
        }
        QuickBooksItemsParser.decodeManyResponseFrom(json = res.bodyAsText())
    }

    fun create(company: QuickBooksCompany, params: List<QuickBooksItemParams>) = scope.later {
        params.map { param ->
            async { create(company, param).await() }
        }.awaitAll()
    }

    fun create(company: QuickBooksCompany, params: QuickBooksItemParams) = scope.later {
        val (income, expense) = accounts.getOrCreate(
            company, listOf(
                InventoryItemIncomeAccountParams(
                    name = "Income From External Invoice Items - ${params.currency}",
                    params.currency
                ),
                InventoryItemExpenseAccountParam(
                    name = "Expenses of External Invoice Items - ${params.currency}",
                    params.currency
                )
            )
        ).await()
        val map = QuickBooksItemsParser.encodeToQuickBooksParams(params.name, params.type, income, expense)
        val json = post(company, Service.Endpoint.Item, map)
        QuickBooksItemsParser.decodeSingleResponseFrom(json, params.amount)
    }
}