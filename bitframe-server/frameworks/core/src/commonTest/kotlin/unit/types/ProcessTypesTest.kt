package unit.types

import bitframe.server.types.processTypes
import expect.expect
import kotlin.test.Ignore
import kotlin.test.Test

class ProcessTypesTest {

    class Customer(
        val name: String,
        val email: String
    )

    @Test
    @Ignore
    fun should_process_customer_info() {
        val type = processTypes<Customer>()
        expect(1).toBe(0 + 1)
        expect(type.singular).toBe("customer")
        expect(type.plural).toBe("customers")
        expect(type.fields).toContainElements()
        expect(type.fields.find { it.name == "email" }).toBeNonNull()
    }
}