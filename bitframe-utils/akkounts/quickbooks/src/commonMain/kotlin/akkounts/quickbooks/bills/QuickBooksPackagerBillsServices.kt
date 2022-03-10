package akkounts.quickbooks.bills

import akkounts.QuickBooksCompany
import akkounts.bills.BillsService
import akkounts.quickbooks.QuickBooksService
import later.Later
import payments.requests.Bill

class QuickBooksPackagerBillsServices(
    val service: QuickBooksService,
    val company: QuickBooksCompany
) : BillsService {
    override fun create(bill: Bill): Later<Bill> = service.bills.create(company, bill)
}