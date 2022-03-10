package akkounts.invoices

import kotlinx.collections.interoperable.List
import later.Later
import payments.requests.Invoice

interface InvoiceService {
    fun create(invoice: Invoice): Later<Invoice>
    fun all(): Later<List<Invoice>>
}