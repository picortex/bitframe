package unit

import bitframe.DataSource
import bitframe.InMemoryDataSource
import bitframe.Sandbox
import bitframe.StaticModule
import bitframe.actions.Action
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import expect.expect
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.http.HttpStatusCode.Companion.OK
import kotlin.test.Test

class GenericModuleTest {
    class Customer(val name: String)

    val db: DataSource = InMemoryDataSource()

    @Test
    fun should_have_a_route_scoped_under_the_module() {
        val createAction = Action(
            name = "create",
            params = mapOf(
                "name" to null,
                "email" to null
            ),
            route = HttpRoute(Post, "/") {
                HttpResponse(OK)
            }
        )
        val module = StaticModule("Customers", createAction)
        val routes = module.actions.map { it.route }
        val route = routes.find { it.path == "/customers" && it.method == Post }
        println("Found")
        for (r in routes) println(r.path)
//        expect(route).toBeNonNull()
    }

    @Test
    fun should_build_a_module_as_a_list_of_actions() {
        val createAction = Action(
            name = "create",
            params = mapOf(
                "name" to null,
                "email" to null
            ),
            route = HttpRoute(Post, "/") {
                HttpResponse(OK)
            }
        )
        val module = StaticModule("Customers", createAction)
        val sandbox = Sandbox(module)
        val res = sandbox.post("/customers")
        expect(res.status).toBe(OK)
    }
}