package intergration.taxes

import akkounts.quickbooks.QuickBooksService
import payments.requests.*
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

class QuickBooksTaxServiceTest {

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
    fun should_use_an_existing_tax() = runTest {
        val tax = Tax(
            name = "Testing Tax",
            rate = 15,
            agency = TaxAgency("Test Revenue Authority")
        )
        val res = service.tax.create(company, tax).await()
        expect(res).toBeNonNull()
    }

    @Test
    fun should_create_a_new_tax() = runTest {
        val now = Clock.System.now().toEpochMilliseconds()
        val tax = Tax(
            name = "Testing Tax - $now",
            rate = 15,
            agency = TaxAgency("Test Revenue Authority")
        )
        val res = service.tax.create(company, tax).await()
        expect(res).toBeNonNull()
    }

    @Test
    fun should_print_all_tax_codes() = runTest {
        val res = service.tax.codes.allRaw(company).await()
        println(Mapper.encodeToString(res))
    }
}