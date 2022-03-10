package akkounts.quickbooks.utils

import kash.Currency
import kotlinx.datetime.LocalDate
import payments.requests.*

fun Invoice.copy(
    uid: String = this.uid,
    header: Header = this.header,
    body: Body = this.body,
) = Invoice(uid, header, body)

fun Bill.copy(
    uid: String = this.uid,
    header: Header = this.header,
    body: Body = this.body,
) = Bill(uid, header, body)

fun Sender.copy(
    uid: String = this.uid,
    name: String = this.name,
    address: Address = this.address,
    ref: VendorReference = this.ref,
    logo: String? = this.logo,
) = Sender(uid, name, address, ref, logo)

fun Receiver.copy(
    uid: String = this.uid,
    name: String = this.name,
    address: Address = this.address,
    ref: VendorReference = this.ref,
) = Receiver(uid, name, address, ref)

fun Header.copy(
    sender: Sender = this.sender,
    receiver: Receiver = this.receiver,
    currency: Currency = this.currency,
    createdOn: LocalDate = this.createdOn,
    dueOn: LocalDate = this.dueOn,
    vendor: Vendor = this.vendor,
    ref: VendorReference = this.ref,
) = Header(sender, receiver, currency, createdOn, dueOn, vendor, ref)