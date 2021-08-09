package unit.modules

import bitframe.Sandbox
import bitframe.modules.authentication.DefaultAuthenticationModule
import expect.expect
import io.ktor.http.*
import kotlin.test.Test

class AuthenticationTest {
    val auth = DefaultAuthenticationModule()
    val sandbox = Sandbox(auth)

    @Test
    fun should_easily_login_a_user() {
        val res = sandbox.post("/authentication/login")
        expect(res.status).toBe(HttpStatusCode.OK)
    }
}