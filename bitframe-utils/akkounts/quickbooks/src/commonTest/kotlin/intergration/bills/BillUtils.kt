package intergration.bills

import akkounts.utils.today
import kash.Currency
import payments.requests.*
import kotlinx.datetime.Clock

fun testBill(): Bill {
    val now = Clock.System.now().toEpochMilliseconds()
    val header = Header(
        sender = Sender(
            uid = "invoice-sender",
            name = "Test Supplier",
            address = Address.Description("Sender Address")
        ),
        receiver = Receiver(
            uid = "invoice-receiver",
            name = "Test Receiver",
            address = Address.Description("Receiver Address")
        ),
        currency = Currency.AUD,
        createdOn = today()
    )
    val tax = Tax(
        name = "Testing Tax",
        rate = 15,
        agency = TaxAgency("TDD Tax Authority")
    )
    val body = Body(
        LineItem.Service(
            uid = "Test Service - $now",
            details = "A Test Service - $now",
            amount = 200000,
            tax = tax
        ),
    )
    return Bill("<unset>", header, body)
}