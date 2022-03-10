package akkounts.quickbooks.payments

import akkounts.payments.PaymentType
import payments.requests.*
import kash.Money

class PaymentParams(
    val amount: Money,
    val type: PaymentType,
    val invoice: Invoice
)