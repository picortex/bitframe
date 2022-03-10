package akkounts.quickbooks.taxes

import akkounts.QuickBooksCompany
import akkounts.QuickBooksTokenStorage
import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.Service
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import later.Later
import later.await
import later.later
import payments.requests.Tax

class QuickBooksTaxRatesService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {
    val parser = QuickBooksTaxRateParser()

    val agencies = QuickBooksTaxAgenciesService(
        clientId, clientSecret, redirectUrl, storage, environment, version, client, scope
    )

    internal fun createRaw(company: QuickBooksCompany, tax: Tax): Later<Map<String, *>> = scope.later {
        val agency = agencies.getOrCreateRaw(company, tax.agency).await()
        val params = mapOf(
            "TaxCode" to "${tax.agency.name} - ${tax.rate}% ${tax.name}",
            "TaxRateDetails" to listOf(
                mapOf(
                    "RateValue" to "${tax.rate}",
                    "TaxApplicableOn" to "Purchase",
                    "TaxAgencyId" to agency["Id"],
                    "TaxRateName" to "${tax.agency.name} - ${tax.rate}% ${tax.name} (Purchase)"
                ),
                mapOf(
                    "RateValue" to "${tax.rate}",
                    "TaxApplicableOn" to "Sales",
                    "TaxAgencyId" to agency["Id"],
                    "TaxRateName" to "${tax.agency.name} - ${tax.rate}% ${tax.name} (Sales)"
                )
            )
        )
        try {
            post(
                company = company,
                endpoint = Service.Endpoint.Tax.Code,
                params = params
            )
        } catch (cause: Throwable) {
            throw IllegalArgumentException("Failed to create tax rates", cause)
        }
    }

    fun create(company: QuickBooksCompany, tax: Tax) = scope.later {
        createRaw(company, tax).await()
        tax
    }

    fun findRaw(company: QuickBooksCompany, tax: Tax): Later<Map<String, *>?> = scope.later {
        val list = allRaw(company).await()
        list.firstOrNull { it["Name"] == "${tax.agency.name}-${tax.name}" }
    }

    fun allRaw(company: QuickBooksCompany) = scope.later {
        val resp = query(company, "Select * from TaxRate")
        parser.parseRaw(resp)
    }
}