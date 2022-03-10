package akkounts.invoices

import akkounts.provider.Vendor
import akkounts.unimplemented.utils.Unimplemented
import kotlinx.collections.interoperable.List
import later.Later
import payments.requests.Invoice

class UnimplementedInvoiceService(val vendor: Vendor) : InvoiceService {
    private val feature = "Invoices"
    override fun create(invoice: Invoice): Later<Invoice> {
        Unimplemented(vendor, feature)
    }

    override fun all(): Later<List<Invoice>> {
        Unimplemented(vendor, feature)
    }
}