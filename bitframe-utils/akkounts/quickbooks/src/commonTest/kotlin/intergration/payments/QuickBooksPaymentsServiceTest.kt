package intergration.payments

import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.payments.PaymentParams
import akkounts.payments.PaymentType
import expect.expect
import kash.AUD
import kotlinx.serialization.mapper.Mapper
import later.await
import intergration.TokenStorage
import intergration.invoice.testInvoice
import intergration.auth.CLIENT_ID
import intergration.auth.CLIENT_SECRET
import intergration.auth.COMPANY_AU
import intergration.auth.REDIRECT_URL
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class QuickBooksPaymentsServiceTest {

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
    fun should_record_a_new_cash_payment() = runTest {
        val invoice = service.invoices.create(company, testInvoice()).await()
        val params = PaymentParams(
            amount = 25.AUD,
            type = PaymentType.Cash,
            invoice = invoice
        )
        val res = service.payments.createRaw(company, params).await()
        println(Mapper.encodeToString(res))
        expect(res).toBeNonNull()
    }

    @Test
    fun can_make_two_payments_for_one_invoice() = runTest {
        val invoice = service.invoices.create(company, testInvoice()).await()
        val params = PaymentParams(
            amount = 575.AUD,
            type = PaymentType.Cash,
            invoice = invoice
        )
        val pay1 = service.payments.createRaw(company, params).await()
        val pay2 = service.payments.createRaw(company, params).await()
        val pay3 = service.payments.createRaw(company, params).await()
        val pay4 = service.payments.createRaw(company, params).await()
        println(Mapper.encodeToString(listOf(pay1, pay2, pay3, pay4)))
        expect(pay1).toBeNonNull()
        expect(pay2).toBeNonNull()
    }
}