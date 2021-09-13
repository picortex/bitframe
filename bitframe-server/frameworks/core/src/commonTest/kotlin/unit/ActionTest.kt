package unit

import bitframe.Sandbox
import bitframe.server.actions.Action
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import expect.expect
import io.ktor.http.*
import kotlinx.coroutines.runTest
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
    fun should_handle_requests_well() = runTest {
        val sandbox = Sandbox(action)
        val res = sandbox.post("/customer")
        expect(res.status).toBe(HttpStatusCode.OK)
    }
}