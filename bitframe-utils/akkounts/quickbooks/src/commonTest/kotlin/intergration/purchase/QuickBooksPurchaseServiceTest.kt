package intergration.purchase

import akkounts.quickbooks.QuickBooksService
import akkounts.payments.PaymentType
import akkounts.quickbooks.purchase.PurchaseParams
import expect.expect
import payments.requests.*
import kash.Currency
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

class QuickBooksPurchaseServiceTest {

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
    fun should_record_a_new_cash_purchase() = runTest {
        val tax = Tax(
            name = "Purchase Tax",
            rate = 10,
            agency = TaxAgency("TDD Tax Authority")
        )
        val params = PurchaseParams(
            payment = PaymentType.Cash,
            currency = Currency.AUD,
            LineItem.Generic(
                details = "Testing detail",
                amount = 100000,
                tax = tax
            )
        )
        val res = service.purchases.createRaw(company, params).await()
        println(Mapper.encodeToString(res))
        expect(res).toBeNonNull()
    }
}