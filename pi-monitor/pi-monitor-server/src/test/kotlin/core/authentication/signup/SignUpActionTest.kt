package core.authentication.signup

import bitframe.*
import bitframe.authentication.users.UsersService
import bitframe.authentication.users.UsersServiceImpl
import bitframe.server.data.DAOProvider
import bitframe.server.modules.authentication.AuthenticationService
import bitframe.server.modules.authentication.AuthenticationServiceImpl
import bitframe.service.config.ServiceConfig
import expect.expect
import io.ktor.http.*
import kotlinx.coroutines.runTest
import pimonitor.authentication.signup.DefaultSignUpAction
import pimonitor.authentication.signup.SignUpController
import kotlin.test.Test

open class SignUpActionTest(component: ComponentUnderTest) {
    constructor(controller: SignUpController) : this(ActionUnderTest(DefaultSignUpAction(controller)))
    constructor(service: UsersService) : this(SignUpController(service))
    constructor(daoProvider: DAOProvider) : this(UsersServiceImpl(daoProvider.users, daoProvider.spaces, ServiceConfig("")))

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