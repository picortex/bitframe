package intergration.tax.rates

import expect.expect
import payments.requests.*
import kotlinx.datetime.Clock
import intergration.utils.quickBooksServiceTest
import later.await
import kotlin.test.Test

class TaxRatesTest {
    private val now get() = Clock.System.now().epochSeconds

    @Test
    fun can_create_a_random_tax_code() = quickBooksServiceTest { company ->
        val args = Tax(
            name = "Value Added Tax - $now",
            rate = 15,
            agency = TaxAgency("Tanzania Revenue Authority")
        )
        val tax = tax.rates.create(company, args).await()
        println(tax)
        expect(tax).toBeNonNull()
    }
}