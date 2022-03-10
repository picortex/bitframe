package akkounts.bills

import later.Later
import payments.requests.Bill

interface BillsService {
    fun create(bill: Bill): Later<Bill>
}