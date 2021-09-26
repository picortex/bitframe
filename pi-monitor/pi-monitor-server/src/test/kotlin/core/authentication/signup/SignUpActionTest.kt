package core.authentication.signup

import bitframe.*
import bitframe.server.data.DAOProvider
import bitframe.server.modules.authentication.AuthenticationModule
import bitframe.server.modules.authentication.AuthenticationService
import bitframe.server.modules.authentication.DefaultAuthenticationService
import expect.expect
import io.ktor.http.*
import kotlinx.coroutines.runTest
import pimonitor.PiMonitorServer
import pimonitor.authentication.signup.DefaultSignUpAction
import pimonitor.authentication.signup.SignUpController
import java.io.File
import kotlin.test.Test

open class SignUpActionTest(val component: ComponentUnderTest) {
    constructor(controller: SignUpController) : this(ActionUnderTest(DefaultSignUpAction(controller)))
    constructor(signInService: AuthenticationService) : this(SignUpController(signInService))
    constructor(daoProvider: DAOProvider) : this(DefaultAuthenticationService(daoProvider))

    val sandbox = Sandbox(component)

    @Test
    fun should_properly_return_descriptive_errors() = runTest {
        val params = mapOf(
            "name" to "Anderson",
            "email" to "johndoe.com",
            "password" to "12345"
        )
        val res = sandbox.post("/api/authentication/sign-up", body = params)
        res.body.printJson()
        expect(res.body.contains("johndoe.com is not a valid email or phone")).toBe(true)
    }

    @Test
    fun should_sign_up_an_individual_properly() = runTest {
        val params = mapOf(
            "name" to "Anderson",
            "email" to "john@doe.com",
            "password" to "12345"
        )
        val res = sandbox.post("/api/authentication/sign-up", body = params)
        res.body.printJson()
        expect(res.status).toBe(HttpStatusCode.Created)
    }
}