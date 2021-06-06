import bitframe.DataSource
import bitframe.InMemoryDataSource
import bitframe.Sandbox
import bitframe.StaticModule
import expect.expect
import expect.toBe
import io.ktor.http.*
import kotlin.test.Test

class GenericModuleTest {
    class Customer(val name: String)

    val db: DataSource = InMemoryDataSource()
    val sandbox = Sandbox(StaticModule.create<Customer>(dataSource = db))

    @Test
    fun get_should_return_a_valid_response() {
        val response = sandbox.get("/customers")
        expect(response.status).toBe(HttpStatusCode.OK)
    }
}