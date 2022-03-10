package intergration.taxes

import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.Service
import expect.expect
import kotlinx.datetime.Clock
import kotlinx.serialization.mapper.Mapper
import later.await
import intergration.TokenStorage
import intergration.auth.CLIENT_ID
import intergration.auth.CLIENT_SECRET
import intergration.auth.COMPANY_AU
import intergration.auth.REDIRECT_URL
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class QuickBooksTaxRateServiceTest {

    private val storage = TokenStorage()

    private val service = QuickBooksService(
        clientId = CLIENT_ID,
        clientSecret = CLIENT_SECRET,
        redirectUrl = REDIRECT_URL,
        storage = storage,
        environment = QuickBooksService.Environment.TEST
    )

    private var company = COMPANY_AU

    @BeforeTest
    fun authorize() = runTest {
        service.refreshTokens(company).await().let {
            println(it)
            company = it
        }
    }

    @Test
    fun should_create_a_new_tax_code() = runTest {
        val now = Clock.System.now().toEpochMilliseconds()
        val name = "Anderson Tax -$now"
        val params = mapOf(
            "TaxCode" to name,
            "TaxRateDetails" to listOf(
                mapOf(
                    "RateValue" to "10",
                    "TaxApplicableOn" to "Sales",
                    "TaxAgencyId" to "1",
                    "TaxRateName" to "Anderson Tax Rate -$now"
                )
            )
        )
        println(Mapper.encodeToString(params))
        val res = service.tax.rates.post(
            company = company,
            endpoint = Service.Endpoint.Tax.Code,
            params = params
        )
        println(Mapper.encodeToString(res))
        expect(res).toBeNonNull()
    }

    @Test
    fun should_retrieve_all_available_taxes() = runTest {
        val rates = service.tax.rates.allRaw(company).await()
        println(Mapper.encodeToString(rates))
    }
}