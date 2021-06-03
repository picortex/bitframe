import bitframe.Application
import bitframe.Sandbox
import bitframe.Module
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import expect.expect
import expect.toBe
import io.ktor.http.*
import kotlin.test.Test

class ApplicationSandboxTest {

    val app = Application(listOf())
    val sandbox = Sandbox(app)

    @Test
    fun get_request_should_return_a_null_body_on_an_un_configured_route() {
        val response = sandbox.get("/customer-un_configured")
        expect(response.status).toBe(HttpStatusCode.NotFound)
        expect(response.body).toBe(null)
    }

    @Test
    fun post_request_should_return_a_null_body_on_an_un_configured_route() {
        val response = sandbox.post("/customer-un_configured")
        expect(response.status).toBe(HttpStatusCode.NotFound)
        expect(response.body).toBe(null)
    }

    class CustomersModule : Module {
        override val routes: List<HttpRoute> = listOf(
            HttpRoute(HttpMethod.Get, "/customers") {
                HttpResponse(HttpStatusCode.OK, "works")
            }
        )
    }

    @Test
    fun get_request_to_a_configured_route_returns_the_configured_result() {
        val application = Application(listOf(CustomersModule()))
        val sandbox = Sandbox(application)
        val response = sandbox.get("/customers")
        expect(response.status).toBe(HttpStatusCode.OK)
        expect(response.body).toBe("works")
    }

    @Test
    fun post_request_to_a_get_route_should_fail() {
        val application = Application(listOf(CustomersModule()))
        val sandbox = Sandbox(application)
        val response = sandbox.post("/customers")
        expect(response.status).toBe(HttpStatusCode.NotFound)
        expect(response.body).toBe(null)
    }
}