package intergration.tax

import akkounts.quickbooks.QuickBooksService
import expect.expect
import payments.requests.*
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

class TaxingTest {

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
    fun can_get_or_create_a_random_tax() = runTest {
        val tax = Tax(
            name = "Value Added Tax",
            rate = 12,
            agency = TaxAgency("Tanzania Revenue Authority")
        )

        val res1 = service.tax.getOrCreateRaw(company, tax).await()
        println(Mapper.encodeToString(res1))
        expect(res1).toBeNonNull()

        val res1Id = res1["Id"] ?: res1["TaxCodeId"]

        val res2 = service.tax.getOrCreateRaw(company, tax).await()
        println(Mapper.encodeToString(res2))

        val res2Id = res2["Id"] ?: res2["TaxCodeId"]
        expect(res2).toBeNonNull()

        expect(res1Id).toBe(res2Id)
    }
}