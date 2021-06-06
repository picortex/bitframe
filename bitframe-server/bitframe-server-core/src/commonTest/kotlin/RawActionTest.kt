import bitframe.Sandbox
import bitframe.actions.RawAction
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import expect.expect
import expect.toBe
import io.ktor.http.*
import kotlin.test.Test

class RawActionTest {
    val action = RawAction(
        name = "Create",
        route = HttpRoute(HttpMethod.Post, "/customer") {
            HttpResponse(HttpStatusCode.OK)
        }
    )

    @Test
    fun should_handle_requests_well() {
        val sandbox = Sandbox(action)
        val res = sandbox.post("/customer")
        expect(res.status).toBe(HttpStatusCode.OK)
    }
}