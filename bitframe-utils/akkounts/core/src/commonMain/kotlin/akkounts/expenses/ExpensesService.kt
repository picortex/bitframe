package akkounts.expenses

import later.Later

interface ExpensesService {
    fun create(expense: ExpenseParams): Later<Map<String, Any?>>
}