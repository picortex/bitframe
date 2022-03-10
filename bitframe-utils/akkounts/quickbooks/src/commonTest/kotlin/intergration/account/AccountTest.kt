package intergration.account

import akkounts.quickbooks.accounts.InventoryItemAssetAccountParams
import akkounts.quickbooks.accounts.InventoryItemExpenseAccountParam
import akkounts.quickbooks.accounts.InventoryItemIncomeAccountParams
import expect.expect
import intergration.packager.utils.quickBooksPackagerTest
import kash.Currency
import kotlinx.datetime.Clock
import later.await
import intergration.utils.quickBooksServiceTest
import kotlin.test.Test
import kotlin.test.assertFails

class AccountTest {
    private val now get() = Clock.System.now().epochSeconds

    @Test
    fun can_create_an_account_of_type_Other_Current_Asset() = quickBooksServiceTest { company ->
        val params = InventoryItemAssetAccountParams(
            name = "Shady Test Asset Account $now",
            currency = Currency.TZS
        )
        val account = accounts.create(company, params).await()
        expect(account.name).toBe(params.name)
    }

    @Test
    fun can_create_an_inventory_income_account_of_type_Sales_of_Product_Income() = quickBooksServiceTest { company ->
        val params = InventoryItemIncomeAccountParams(
            name = "Shady Test Income Account $now",
            currency = Currency.TZS
        )
        val account = accounts.create(company, params).await()
        expect(account.name).toBe(params.name)
    }

    @Test
    fun can_create_accounts_in_bulk() = quickBooksServiceTest { company ->
        val list = (10..15).map {
            InventoryItemIncomeAccountParams(
                name = "TestIncome$it - $now",
                currency = Currency.TZS
            )
        }
        val accounts = accounts.create(company, list).await()
        expect(accounts.size).toBe(6)
    }

    @Test
    fun can_query_an_account() = quickBooksServiceTest { company ->
        val account = accounts.search(company, name = "Shady 9 Account").await()
        println(account)
    }

    @Test
    fun should_get_or_create_an__imported_expense_account() = quickBooksServiceTest { company ->
        val params = InventoryItemExpenseAccountParam(name = "Imported Expenses", currency = Currency.ZAR)
        val account1 = accounts.getOrCreate(company, params).await()
        expect(account1.id).toBeNonNull()
        val account2 = accounts.getOrCreate(company, params).await()
        expect(account2.id).toBeNonNull()
        expect(account2.id).toBe(account1.id)
    }

    @Test
    fun should_not_crash_when_searching_a_missing_account() = quickBooksServiceTest { company ->
        val account = accounts.search(company, now.toString()).await()
        expect(account.firstOrNull()).toBeNull()
    }

    @Test
    fun should_get_or_create_account_without_conflicting_currencies() = quickBooksServiceTest { company ->
        val name = "Get or Create Test - $now"
        val params1 = InventoryItemExpenseAccountParam(name = name, currency = Currency.ZAR)
        accounts.getOrCreate(company, params1).await()
        val throwable = assertFails {
            val params2 = InventoryItemExpenseAccountParam(name = name, currency = Currency.TZS)
            accounts.getOrCreate(company, params2).await()
        }
        expect(throwable.message?.contains("Conflicting currencies")).toBe(true)
    }
}