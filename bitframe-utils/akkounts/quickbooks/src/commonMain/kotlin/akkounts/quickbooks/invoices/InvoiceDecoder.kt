package akkounts.quickbooks.invoices

import akkounts.QuickBooksCompany
import akkounts.quickbooks.customers.QuickBooksCustomer
import akkounts.quickbooks.utils.copy
import akkounts.quickbooks.vendors.QuickBooksVendor
import akkounts.quickbooks.utils.listOf
import akkounts.utils.unset
import payments.requests.*
import kash.Currency
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.datetime.LocalDate

internal object InvoiceDecoder {

    val VENDOR = Vendor("QuickBooks")

    fun decodeSender(company: QuickBooksCompany) = Sender(
        uid = unset,
        name = company.realmId,
        address = Address.Description("Unknown"),
        ref = VendorReference(company.realmId, company.realmId)
    )

    fun decodeReceiver(customerRef: Map<String, String>, shipFrom: Map<String, String>?) = Receiver(
        name = customerRef["name"].toString(),
        uid = customerRef["value"].toString(),
        address = Address.Description(shipFrom?.get("Line1") ?: unset),
        ref = VendorReference(
            name = customerRef["name"].toString(),
            uid = customerRef["value"].toString()
        ),
    )

    fun decodeHeader(company: QuickBooksCompany, map: Map<String, *>) = Header(
        sender = decodeSender(company),
        receiver = decodeReceiver(
            customerRef = map["CustomerRef"] as Map<String, String>,
            shipFrom = map["ShipFromAddr"] as? Map<String, String>
        ),
        currency = Currency.valueOf((map["CurrencyRef"] as Map<String, String>)["value"].toString()),
        createdOn = LocalDate.parse(
            (map["MetaData"] as Map<String, String>)["CreateTime"].toString().split("T").first()
        ),
        dueOn = LocalDate.parse(map["DueDate"].toString()),
        ref = VendorReference(uid = map["Id"].toString(), name = unset),
        vendor = VENDOR
    )

    fun decodeSalesItemLineDetail(
        lineDetail: Map<String, *>,
        taxes: List<Map<String, *>>
    ): LineItem {
        val itemRef = lineDetail["ItemRef"] as? Map<String, String> ?: return LineItem.Generic(details = unset, 0L)
        val uid = itemRef["value"].toString()
        val name = itemRef["name"].toString()

        val tax = taxes.first()

        val taxDetail = Tax(
            name = unset,
            rate = (tax["TaxLineDetail"] as Map<String, String>)["TaxPercent"].toString().toInt(),
            agency = TaxAgency(unset)
        )

        return LineItem.Product(
            uid = uid,
            name = name,
            unitPrice = ((lineDetail["UnitPrice"]?.toString()?.toDouble() ?: 0.0) * 100).toLong(),
            quantity = lineDetail["Qty"]?.toString()?.toInt() ?: 0,
            tax = taxDetail
        )
    }

    fun decodeBody(lines: List<Map<String, *>>, taxes: List<Map<String, *>>): Body {
        val items = lines.map { item ->
            when (item["DetailType"]) {
                "SalesItemLineDetail" -> decodeSalesItemLineDetail(
                    lineDetail = item["SalesItemLineDetail"] as Map<String, *>,
                    taxes = taxes
                )
                else -> LineItem.Generic(
                    details = "Item no: ${item["Id"]}",
                    amount = item["Amount"].toString().toDouble().toLong() * 100
                )
            }
        }
        return Body(items.toInteroperableList())
    }

    fun decodeSingle(
        company: QuickBooksCompany,
        map: Map<String, *>
    ) = Invoice(
        uid = unset,
        header = decodeHeader(company, map),
        body = decodeBody(
            lines = map["Line"] as List<Map<String, *>>,
            taxes = (map["TxnTaxDetail"] as Map<String, *>)["TaxLine"] as List<Map<String, *>>
        )
    )

    fun decodeManyResponse(
        company: QuickBooksCompany,
        response: Map<String, *>
    ) = response.listOf("Invoice").map { decodeSingle(company, it) }

    fun decodeManyResponseRaw(response: Map<String, *>) = response.listOf("Invoice")

    fun decodeSingleResponse(
        invoice: Invoice,
        company: QuickBooksCompany,
        customer: QuickBooksCustomer,
        response: Map<String, *>
    ): Invoice {
        val sender = invoice.header.sender.copy(
            ref = VendorReference(
                uid = company.realmId,
                name = company.realmId
            )
        )

        val receiver = invoice.header.receiver.copy(
            ref = VendorReference(
                uid = customer.id,
                name = customer.displayName
            )
        )
        val uid = (response["Invoice"] as Map<String, *>)["Id"].toString()
        val header = invoice.header.copy(
            sender = sender,
            receiver = receiver,
            ref = VendorReference(uid, uid),
            vendor = VENDOR
        )
        return invoice.copy(
            header = header,
        )
    }
}