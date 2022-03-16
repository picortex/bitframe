package intergration.packager

import akkounts.accounts.AccountParams
import akkounts.accounts.types.Asset
import akkounts.expenses.ExpenseParams
import akkounts.payments.PaymentType
import expect.expect
import intergration.bills.testBill
import intergration.invoice.testInvoice
import intergration.packager.utils.quickBooksPackagerTest
import payments.requests.*
import kash.Currency
import kotlinx.datetime.Clock
import later.await
import kotlin.test.Test

class SyntaxTest {

    private val now get() = Clock.System.now().epochSeconds

    @Test
    fun should_create_an_accounts_easily() = quickBooksPackagerTest {
        val params = AccountParams(
            name = "Shady Test Asset Account $now",
            type = Asset.Current.Inventory,
            currency = Currency.TZS
        )
        val account = accounts.create(params).await()
        expect(account.name).toBe(params.name)
    }

    @Test
    fun should_create_an_invoice_easily() = quickBooksPackagerTest {
        val invoice = invoices.create(testInvoice()).await()
        expect(invoice).toBeNonNull()
    }

    @Test
    fun should_create_a_bill_easily() = quickBooksPackagerTest {
        val bills = bills.create(testBill()).await()
        expect(bills).toBeNonNull()
    }

    @Test
    fun should_create_expenses_easily() = quickBooksPackagerTest {
        val params = ExpenseParams(
            payment = PaymentType.Cash,
            currency = Currency.AUD,
            LineItem.Generic("Test Expense Item", 300000)
        )
        val expense = expenses.create(params).await()
        expect(expense)
    }
}