package types

import bitframe.types.processTypes
import expect.expect
import expect.toBe
import expect.toBeNonNull
import expect.toContainElements
import kotlin.test.Test

class ProcessTypesTest {

    class Customer(
        val name: String,
        val email: String
    )

    @Test
    fun should_process_customer_info() {
        val type = processTypes<Customer>()
        expect(type.singular).toBe("customer")
        expect(type.plural).toBe("customers")
        expect(type.fields).toContainElements()
        expect(type.fields.find { it.name == "email" }).toBeNonNull()
    }
}