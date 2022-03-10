package akkounts.quickbooks.accounts

import akkounts.QuickBooksCompany
import akkounts.QuickBooksTokenStorage
import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.Service
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import later.Later
import later.await
import later.later
import kotlin.jvm.JvmOverloads

class QuickBooksAccountsService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {
    private val encoder = AccountsEncoder
    private val decoder = AccountsDecoder

    @OptIn(ExperimentalStdlibApi::class)
    fun getOrCreate(
        company: QuickBooksCompany,
        list: List<QuickBooksAccountParams>
    ): Later<List<QuickBooksAccount>> = scope.later {
        val requests = list.map {
            async { getOrCreateSuspending(company, it) }
        }
        buildList {
            for (req in requests) {
                add(req.await())
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun create(
        company: QuickBooksCompany,
        list: List<QuickBooksAccountParams>
    ): Later<List<QuickBooksAccount>> = scope.later {
        val requests = list.map {
            async { createSuspending(company, it) }
        }
        buildList {
            for (req in requests) {
                add(req.await())
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private suspend fun createSuspending(
        company: QuickBooksCompany,
        params: QuickBooksAccountParams
    ): QuickBooksAccount = try {
        val args = encoder.encode(params)
        val map = post(company, Service.Endpoint.Account, args)
        decoder.decodeSingleResponseFrom(map)
    } catch (cause: Throwable) {
        throw Exception("Failed to create account '${params.name}' with quickbooks", cause)
    }

    fun create(company: QuickBooksCompany, params: QuickBooksAccountParams) = scope.later {
        createSuspending(company, params)
    }

    private suspend fun getOrCreateSuspending(company: QuickBooksCompany, params: QuickBooksAccountParams): QuickBooksAccount {
        val existingAccount = search(company, name = params.name).await().firstOrNull()
        if (existingAccount != null && existingAccount.currency != params.currency) {
            throw IllegalArgumentException("Conflicting currencies\nExisting: $existingAccount\nProposal: $params")
        }
        return existingAccount ?: createSuspending(company, params)
    }

    fun getOrCreate(company: QuickBooksCompany, params: QuickBooksAccountParams) = scope.later {
        getOrCreateSuspending(company, params)
    }

    fun search(company: QuickBooksCompany, name: String) = scope.later {
        try {
            val query = "select * from Account where Name='$name'"
            val json = query(company, query)
            decoder.decodeManyResponseFrom(json)
        } catch (e: Throwable) {
            throw Exception("Failed to query account(name=$name) from quickbooks", e)
        }
    }
}