package akkounts.quickbooks.accounts

import akkounts.QuickBooksCompany
import akkounts.accounts.Account
import akkounts.accounts.AccountParams
import akkounts.accounts.AccountsService
import akkounts.expenses.ExpenseParams
import akkounts.expenses.ExpensesService
import akkounts.quickbooks.QuickBooksService
import later.Later
import later.await
import later.later

class QuickBooksPackageAccountsService(
    private val service: QuickBooksService,
    private val company: QuickBooksCompany
) : AccountsService {
    override fun create(params: AccountParams): Later<Account> = service.scope.later {
        service.accounts.create(company, params.toQuickBooksAccountParams()).await().toAccount()
    }

    override fun getOrCreate(params: AccountParams): Later<Account> {
        TODO("Not yet implemented")
    }

    override fun search(name: String): Later<List<Account>> {
        TODO("Not yet implemented")
    }
}