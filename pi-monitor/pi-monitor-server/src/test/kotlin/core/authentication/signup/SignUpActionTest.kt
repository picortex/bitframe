package core.authentication.signup

import bitframe.Sandbox
import expect.expect
import io.ktor.http.*
import kotlinx.coroutines.runTest
import pimonitor.authentication.signup.DefaultSignUpAction
import pimonitor.authentication.signup.SignUpController
import kotlin.test.Test

open class SignUpActionTest {
    val action = DefaultSignUpAction(SignUpController())
    val sandbox = Sandbox(action)

    @Test
    fun should_sign_up_an_individual_properly() = runTest {
        val res = sandbox.post("/api/authentication/sign-up")
        expect(res.status).toBe(HttpStatusCode.OK)
    }
}