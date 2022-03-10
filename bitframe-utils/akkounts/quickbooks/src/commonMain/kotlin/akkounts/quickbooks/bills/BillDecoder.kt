package akkounts.quickbooks.bills

import akkounts.QuickBooksCompany
import akkounts.quickbooks.utils.copy
import akkounts.quickbooks.vendors.QuickBooksVendor
import payments.requests.Bill
import payments.requests.VendorReference

internal object BillDecoder {
    fun decodeSingleResponse(
        bill: Bill,
        company: QuickBooksCompany,
        vendor: QuickBooksVendor,
        res: Map<String, *>
    ): Bill {
        val sender = bill.header.sender.copy(
            ref = VendorReference(uid = vendor.id, name = vendor.givenName)
        )
        val receiver = bill.header.receiver.copy(
            ref = VendorReference(uid = company.realmId, name = company.realmId)
        )
        val billResponse = res["Bill"] as Map<String, *>
        val uid = billResponse["Id"].toString()
        return bill.copy(uid = uid, header = bill.header.copy(sender, receiver, ref = VendorReference(uid, uid)))
    }
}