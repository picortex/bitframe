package intergration.bills

import expect.expect
import intergration.utils.quickBooksServiceTest
import later.await
import kotlin.test.Test

class BillsTest {

    @Test
    fun can_create_a_bill() = quickBooksServiceTest { company ->
        val bill = testBill()
        val invoice = bills.create(company, bill).await()
        expect(invoice).toBeNonNull()
        println(invoice)
    }
}