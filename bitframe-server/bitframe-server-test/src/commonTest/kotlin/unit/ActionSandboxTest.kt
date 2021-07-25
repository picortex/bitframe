package unit

import bitframe.Sandbox
import bitframe.actions.Action
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import expect.expect
import expect.toBe
import io.ktor.http.*
import kotlin.test.Test

class ActionSandboxTest {
    @Test
    fun can_test_an_individual_sandbox() {
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