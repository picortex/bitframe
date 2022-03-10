package akkounts.expenses

import akkounts.provider.Vendor
import akkounts.unimplemented.utils.Unimplemented
import later.Later

class UnimplementedExpensesService(val vendor: Vendor) : ExpensesService {
    override fun create(expense: ExpenseParams): Later<Map<String, Any?>> {
        Unimplemented(vendor, "Expenses")
    }
}