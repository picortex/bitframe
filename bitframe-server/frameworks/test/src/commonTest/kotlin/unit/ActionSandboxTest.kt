package unit

import bitframe.Sandbox
import bitframe.server.actions.Action
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import expect.expect
import io.ktor.http.*
import kotlinx.coroutines.runTest
import kotlin.test.Test

class ActionSandboxTest {
    @Test
    fun can_test_an_individual_sandbox() = runTest {
        val action = Action(
            name = "Raw",
            params = mapOf(),
            route = HttpRoute(HttpMethod.Post, "/raw") {
                HttpResponse(HttpStatusCode.OK)
            }
        )

        val sandbox = Sandbox(action)
        val res = sandbox.post("/raw", mapOf(), "{}")
        expect(res.status).toBe(HttpStatusCode.OK)
    }
}