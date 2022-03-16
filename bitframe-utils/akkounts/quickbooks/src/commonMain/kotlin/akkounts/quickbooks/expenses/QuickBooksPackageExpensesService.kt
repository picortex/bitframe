package akkounts.quickbooks.expenses

import akkounts.QuickBooksCompany
import akkounts.expenses.ExpenseParams
import akkounts.expenses.ExpensesService
import akkounts.quickbooks.QuickBooksService

class QuickBooksPackageExpensesService(
    private val service: QuickBooksService,
    private val company: QuickBooksCompany
) : ExpensesService {
    override fun create(expense: ExpenseParams) = service.purchases.createRaw(company, expense.toPurchaseParams())
}