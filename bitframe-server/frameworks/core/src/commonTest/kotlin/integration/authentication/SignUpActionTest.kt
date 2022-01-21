package integration.authentication

import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

class SignUpActionTest {
//    private val sandbox = Sandbox(
//        SignInAction(AUTHENTICATION_CONTROLLER_UNDER_TEST)
//    )

    @Test
    @Ignore
    fun should_return_a_valid_error_on_a_user_that_does_not_exist() = runTest {
        val body = mapOf(
            "alias" to "email@test.com",
            "password" to "pass1"
        )
        TODO()
//        val res = sandbox.post("/api/authentication/sign-in", body = body)
//        expect(res.status).toBe(HttpStatusCode.BadRequest)
    }
}