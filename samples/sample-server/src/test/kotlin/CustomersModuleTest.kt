import bitframe.Application
import bitframe.Sandbox
import bitframe.StaticModule
import bitframe.actions.Action
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import expect.expect
import expect.toBe
import expect.toBeNonNull
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.http.HttpMethod.Companion.Put
import io.ktor.http.HttpStatusCode.Companion.OK
import kotlin.test.Test

class CustomersModuleTest {
    val createAction = Action(
        name = "Create",
        params = mapOf(
            "name" to "",
            "email" to ""
        ),
        route = HttpRoute(Post, "/") {
            HttpResponse(OK, "Success")
        }
    )

    val updateAction = Action(
        name = "Update",
        params = mapOf(),
        route = HttpRoute(Put, "/") {
            HttpResponse(OK, it.body ?: "")
        }
    )

    @Test
    fun should_test_an_individual_module() {
        val module = StaticModule("Customers", createAction, updateAction)
        // new StaticModule<Customer>(action) // StaticModule.create<Action>()
        val box = Sandbox(module)
        val headers = mapOf<String, String>()
        println(module.actions.map { it.route }.map { it.path })
        val result1 = box.put("/customers", headers, "")
        expect(result1).toBeNonNull()
        expect(result1.status).toBe(OK)
        expect(result1.body).toBe("Success")

        val body = """{"name":"John Deo", "email":"john@doe.com"}"""

        val result2 = box.put("/customers", mapOf(), body)
        expect(result2).toBe(OK)
    }

    @Test
    fun should_test_an_individual_action() {
        val box = Sandbox(updateAction)
        val body = """{"name":"John Deo", "email":"john@doe.com", "id": 123 }"""
        val res = box.put("/customers", mapOf(), body)
        expect(res.body).toBe(body)
    }

    @Test
    fun should_test_an_individual_application() {
        val app = Application(listOf(StaticModule("Customers", createAction)))
        val box = Sandbox(app)
        val res = box.post("/customer", body = "{}")
        expect(res.status).toBe(OK)
    }
}