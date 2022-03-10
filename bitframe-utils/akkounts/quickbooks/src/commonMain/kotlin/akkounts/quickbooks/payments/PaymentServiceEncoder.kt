package akkounts.quickbooks.payments

import akkounts.quickbooks.accounts.QuickBooksAccount

internal object PaymentServiceEncoder {
    fun encode(params: PaymentParams, account: QuickBooksAccount) = mapOf(
        "TotalAmt" to (params.amount.amount / 100).toDouble(),
        "DepositToAccountRef" to mapOf(
            "value" to account.id
        ),
        "Line" to listOf(
            mapOf(
                "Amount" to (params.amount.amount / 100).toDouble(),
                "LinkedTxn" to listOf(
                    mapOf(
                        "TxnId" to params.invoice.header.ref.uid,
                        "TxnType" to "Invoice"
                    )
                )
            )
        ),
        "CustomerRef" to mapOf(
            "value" to params.invoice.header.receiver.ref.uid
        )
    )
}