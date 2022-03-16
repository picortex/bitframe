package akkounts.expenses

import akkounts.payments.PaymentType
import kash.Currency
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import payments.requests.LineItem

data class ExpenseParams(
    val payment: PaymentType,
    val currency: Currency,
    val items: List<LineItem>
) {
    constructor(
        payment: PaymentType,
        currency: Currency,
        vararg items: LineItem
    ) : this(payment, currency, items.toInteroperableList())
}