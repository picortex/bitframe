package intergration.taxes

import akkounts.quickbooks.QuickBooksService
import expect.expect
import payments.requests.*
import kotlinx.datetime.Clock
import later.await
import intergration.TokenStorage
import intergration.auth.CLIENT_ID
import intergration.auth.CLIENT_SECRET
import intergration.auth.COMPANY_AU
import intergration.auth.REDIRECT_URL
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class QuickBooksTaxAgenciesServiceTest {

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
    fun should_create_a_new_tax_agency() = runTest {
        val now = Clock.System.now().toEpochMilliseconds()
        val res = service.tax.agencies.create(company, TaxAgency("AndersonRevenueAuthority-$now")).await()
        println(res)
        expect(res).toBeNonNull()
    }

    @Test
    fun should_retrieve_all_available_tax_agency() = runTest {
        val rates = service.tax.agencies.all(company).await()
        println(rates)
        expect(rates).toBeNonNull()
    }
}