package unit.purchase

import akkounts.quickbooks.accounts.QuickBooksAccount
import akkounts.payments.PaymentType
import akkounts.quickbooks.purchase.PurchaseEncoder
import akkounts.quickbooks.purchase.PurchaseParams
import akkounts.utils.unset
import payments.requests.*
import kash.Currency
import kotlinx.serialization.mapper.Mapper
import kotlin.test.Test

class PurchaseEncoderTest {
    val params = PurchaseParams(
        payment = PaymentType.Cash,
        currency = Currency.AUD,
        LineItem.Generic(
            details = "Testing detail",
            amount = 100000
        )
    )

    val account = QuickBooksAccount(
        id = unset,
        name = "Test Account",
        type = "Expense",
        currency = Currency.TZS
    )

    val encoder = PurchaseEncoder

    @Test
    fun should_conform_to_quickbooks_api() {
        println(Mapper.encodeToString(encoder.encode(params, account, account)))
    }
}