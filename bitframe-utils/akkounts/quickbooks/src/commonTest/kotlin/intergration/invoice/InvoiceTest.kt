package intergration.invoice

import expect.expect
import intergration.utils.quickBooksServiceTest
import later.await
import kotlin.test.Test

class InvoiceTest {

    @Test
    fun can_create_an_invoice() = quickBooksServiceTest { company ->
        val inv = testInvoice()
        val invoice = invoices.create(company, inv).await()
        expect(invoice).toBeNonNull()
        println(invoice)
    }

    @Test
    fun can_query_invoices() = quickBooksServiceTest { company ->
        val invoices = invoices.all(company).await()
        println(invoices)
    }
}