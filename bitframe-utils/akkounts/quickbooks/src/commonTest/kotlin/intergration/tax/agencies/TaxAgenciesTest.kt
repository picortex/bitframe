package intergration.tax.agencies

import akkounts.quickbooks.QuickBooksService
import expect.expect
import kotlinx.datetime.Clock
import kotlinx.serialization.mapper.Mapper
import later.await
import payments.requests.*
import intergration.TokenStorage
import intergration.auth.CLIENT_ID
import intergration.auth.CLIENT_SECRET
import intergration.auth.COMPANY_AU
import intergration.auth.REDIRECT_URL
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class TaxAgenciesTest {

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

    private val now get() = Clock.System.now().epochSeconds

    @Test
    fun can_create_a_random_tax_agency() = runTest {
        val agency = TaxAgency("Test Agency - $now")
        val res = service.tax.agencies.create(company, agency).await()
        expect(res).toBeNonNull()
    }

    @Test
    fun can_create_a_random_raw_tax_agency() = runTest {
        val agency = TaxAgency("Test Agency - $now")
        val res = service.tax.agencies.createRaw(company, agency).await()
        println(Mapper.encodeToString(res))
        expect(res["Id"]).toBeNonNull()
    }
}