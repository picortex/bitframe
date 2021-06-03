import bitframe.Sandbox
import bitframe.StaticModule
import expect.expect
import expect.toBe
import io.ktor.http.*
import kotlin.test.Test

class GenericModuleTest {
    class CustomerModule : StaticModule("customer", "customers")

    val sandbox = Sandbox(CustomerModule())

    @Test
    fun get_should_return_a_valid_response() {
        val response = sandbox.get("/customers")
        expect(response.status).toBe(HttpStatusCode.OK)
    }
}