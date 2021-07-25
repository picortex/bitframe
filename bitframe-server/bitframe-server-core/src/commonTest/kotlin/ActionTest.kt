import bitframe.Sandbox
import bitframe.actions.Action
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import expect.expect
import expect.toBe
import io.ktor.http.*
import kotlin.test.Test

class ActionTest {
    val action = Action(
        name = "Create",
        params = mapOf(
            "name" to null,
            "email" to null
        ),
        route = HttpRoute(HttpMethod.Post, "/customer") {
            HttpResponse(HttpStatusCode.OK)
        }
    )

    @Test
    fun should_handle_requests_well() {
        val sandbox = Sandbox(action)
        val res = sandbox.put("/customer")
        expect(res.status).toBe(HttpStatusCode.OK)
    }
}