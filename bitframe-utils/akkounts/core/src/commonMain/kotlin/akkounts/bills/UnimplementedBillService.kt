package akkounts.bills

import akkounts.provider.Vendor
import akkounts.unimplemented.utils.Unimplemented
import later.Later
import payments.requests.Bill

class UnimplementedBillService(val vendor: Vendor) : BillsService {
    override fun create(bill: Bill): Later<Bill> {
        Unimplemented(vendor, "Billing")
    }
}