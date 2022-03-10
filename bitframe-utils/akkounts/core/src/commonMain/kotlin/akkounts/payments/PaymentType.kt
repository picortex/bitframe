package akkounts.payments

sealed class PaymentType {
    object Cash : PaymentType()
    object CreditCard : PaymentType()
    object Check : PaymentType()
}
