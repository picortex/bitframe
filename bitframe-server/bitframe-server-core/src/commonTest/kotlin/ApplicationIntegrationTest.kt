import bitframe.*
import bitframe.modules.Customer
import bitframe.utils.decodeListFromString
import expect.expect
import expect.toBe
import expect.toContainElements
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper
import kotlin.test.Test

class ApplicationIntegrationTest {

    val db: DataSource = InMemoryDataSource()

//    @Test
//    fun should_create_successfully_create_an_entity() {
//        val customersModule = StaticModule.create(dataSource = db)
//        val box = Sandbox(customersModule)
//        val customer = Customer(name = "John Doe", email = "john@doe.com")
//        val resp1 = box.post("/customer", mapOf(), Json.encodeToString(customer))
//        expect(resp1.status).toBe(HttpStatusCode.Created)
//        val respMap = Mapper.decodeFromString(resp1.body!!)
//        expect(respMap["name"]).toBe(customer.name)
//    }
//
//    @Test
//    fun should_create_and_return_the_created_entity() {
//        val customersModule = StaticModule.create(dataSource = db)
//        val box = Sandbox(customersModule)
//        val customer = Customer(name = "John Doe", email = "john@doe.com")
//        val resp1 = box.post("/customer", mapOf(), Json.encodeToString(customer))
//        expect(resp1.status).toBe(HttpStatusCode.Created)
//        val resp1Map = Mapper.decodeFromString(resp1.body!!)
//        expect(resp1Map["name"]).toBe(customer.name)
//
//        val resp2 = box.get("/customers")
//        expect(resp2.status).toBe(HttpStatusCode.OK)
//        val resp2List = Mapper.decodeListFromString(resp2.body!!)
//        expect(resp2List).toContainElements()
//        val added = resp2List.first { it["uid"] == resp1Map["uid"] }
//        expect(added["name"]).toBe(resp1Map["name"])
//    }
}