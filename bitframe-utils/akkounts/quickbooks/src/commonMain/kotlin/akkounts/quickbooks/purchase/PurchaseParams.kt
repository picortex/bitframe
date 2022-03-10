package akkounts.quickbooks.purchase

import akkounts.payments.PaymentType
import payments.requests.*
import kash.Currency

data class PurchaseParams(
    val payment: PaymentType,
    val currency: Currency,
    val items: List<LineItem>
) {
    constructor(
        payment: PaymentType,
        currency: Currency,
        vararg items: LineItem
    ) : this(payment, currency, items.toList())
}