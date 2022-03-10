package akkounts.quickbooks.purchase

import akkounts.quickbooks.accounts.QuickBooksAccount
import akkounts.payments.PaymentType

object PurchaseEncoder {
    @OptIn(ExperimentalStdlibApi::class)
    fun encode(
        params: PurchaseParams,
        assetAccount: QuickBooksAccount,
        expenseAccount: QuickBooksAccount
    ) = buildMap<String, Any?> {
        put(
            "PaymentType", when (params.payment) {
                PaymentType.Cash -> "Cash"
                PaymentType.CreditCard -> "CreditCard"
                PaymentType.Check -> "Check"
            }
        )
        put("AccountRef", assetAccount.toAccountRef())
        put("Line", params.items.map {
            mapOf(
                "DetailType" to "AccountBasedExpenseLineDetail",
                "Amount" to it.costAfterTax / 100,
                "AccountBasedExpenseLineDetail" to mapOf(
                    "AccountRef" to expenseAccount.toAccountRef()
                )
            )
        })
    }

    private fun QuickBooksAccount.toAccountRef() = mapOf(
        "name" to name,
        "value" to id
    )
}