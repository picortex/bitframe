import bitframe.Application
import bitframe.ApplicationSandbox
import bitframe.Module
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import expect.expect
import expect.toBe
import io.ktor.http.*
import kotlin.test.Test

class ApplicationSandboxTest {

    val app = Application(listOf())
    val sandbox = ApplicationSandbox(app)

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

    @Test
    fun get_request_to_a_configured_route_returns_the_configured_result() {
        class CustomersModule : Module {
            override val routes: List<HttpRoute> = listOf(
                HttpRoute(HttpMethod.Get, "/customers") {
                    HttpResponse(HttpStatusCode.OK, "works")
                }
            )
        }

        val application = Application(listOf(CustomersModule()))
        val sandbox = ApplicationSandbox(application)
        val response = sandbox.get("/customers")
        expect(response.status).toBe(HttpStatusCode.OK)
        expect(response.body).toBe("works")
    }
}