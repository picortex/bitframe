package integration.authentication

import bitframe.Sandbox
import bitframe.server.modules.authentication.services.signin.SignInAction
import expect.expect
import io.ktor.http.*
import kotlinx.coroutines.runTest
import kotlin.test.Test

class SignUpActionTest {
    private val sandbox = Sandbox(
        SignInAction(AUTHENTICATION_CONTROLLER_UNDER_TEST)
    )

    @Test
    fun should_return_a_valid_error_on_a_user_that_does_not_exist() = runTest {
        val body = mapOf(
            "alias" to "email@test.com",
            "password" to "pass1"
        )
        val res = sandbox.post("/api/authentication/sign-in", body = body)
        expect(res.status).toBe(HttpStatusCode.BadRequest)
    }
}