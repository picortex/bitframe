package unit

import kotlin.test.Test

class ApplicationSandboxTest {

    @Test
    fun should_check_this_method() {
        
    }
//    val app = BitframeApplication(listOf())
//    val sandbox = Sandbox(app)
//
//    @Test
//    fun get_request_should_return_a_null_body_on_an_un_configured_route() {
//        val response = sandbox.get("/customer-un_configured")
//        expect(response.status).toBe(HttpStatusCode.NotFound)
//    }
//
//    @Test
//    fun post_request_should_return_a_null_body_on_an_un_configured_route() {
//        val response = sandbox.put("/customer-un_configured", headers = mapOf(), body = "{}")
//        expect(response.status).toBe(HttpStatusCode.NotFound)
//    }
//
//    class CustomersModule : Module {
//        override val name: String = "Customer"
//        override val actions: List<Action> = listOf(
//            Action("create", mapOf(), HttpRoute(HttpMethod.Post, "/customers") {
//                HttpResponse(HttpStatusCode.OK)
//            }),
//            Action("fetch", mapOf(), HttpRoute(HttpMethod.Get, "/customers") {
//                HttpResponse(HttpStatusCode.OK, "works")
//            })
//        )
//    }
//
//    @Test
//    fun get_request_to_a_configured_route_returns_the_configured_result() {
//        val application = BitframeApplication(listOf(CustomersModule()))
//        val sandbox = Sandbox(application)
//        val response = sandbox.get("/customers")
//        println(sandbox.routes)
//        expect(response.status).toBe(HttpStatusCode.OK)
//        expect(response.body).toBe("works")
//    }
//
//    @Test
//    fun post_request_to_a_get_route_should_fail() {
//        val application = BitframeApplication(listOf(CustomersModule()))
//        val sandbox = Sandbox(application)
//        val response = sandbox.put("/customers", mapOf(), "{}")
//        expect(response.status).toBe(HttpStatusCode.NotFound)
//    }
}