package akkounts.quickbooks.invoices

import akkounts.QuickBooksCompany
import akkounts.invoices.InvoiceService
import akkounts.quickbooks.QuickBooksService
import kotlinx.collections.interoperable.List
import payments.requests.*
import later.Later

class QuickBooksPackagerInvoiceService(
    private val service: QuickBooksService,
    private val company: QuickBooksCompany
) : InvoiceService {
    override fun create(invoice: Invoice): Later<Invoice> = service.invoices.create(company, invoice)
    override fun all(): Later<List<Invoice>> = service.invoices.all(company)
}