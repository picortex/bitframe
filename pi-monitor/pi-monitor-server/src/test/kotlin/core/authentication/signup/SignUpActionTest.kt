package core.authentication.signup

import bitframe.Sandbox
import bitframe.server.actions.Action
import bitframe.server.data.DAOProvider
import bitframe.server.modules.authentication.AuthenticationService
import bitframe.server.modules.authentication.DefaultAuthenticationService
import expect.expect
import io.ktor.http.*
import kotlinx.coroutines.runTest
import pimonitor.authentication.signup.DefaultSignUpAction
import pimonitor.authentication.signup.SignUpController
import kotlin.test.Test

open class SignUpActionTest private constructor(val action: Action) {
    constructor(controller: SignUpController) : this(DefaultSignUpAction(controller))
    constructor(signInService: AuthenticationService) : this(SignUpController(signInService))
    constructor(daoProvider: DAOProvider) : this(DefaultAuthenticationService(daoProvider))

    val sandbox = Sandbox(action)

    @Test
    fun should_sign_up_an_individual_properly() = runTest {
        val res = sandbox.post("/api/authentication/sign-up")
        expect(res.status).toBe(HttpStatusCode.OK)
    }
}