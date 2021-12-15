package core.authentication.signup

import bitframe.*
import expect.expect
import io.ktor.http.*
import kotlinx.coroutines.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
open class SignUpActionTest(component: ComponentUnderTest) {
//    constructor(controller: SignUpController) : this(ActionUnderTest(DefaultSignUpAction(controller)))
//    constructor(service: UsersService) : this(
//        SignUpController(
//            dao = MonitorDaoInMemory(config = InMemoryDaoConfig(0)),
//            config = ServiceConfig(""),
//            service = service,
//            bus = InMemoryEventBus()
//        )
//    )
//
//    constructor(daoProvider: DAOProvider) : this(UsersServiceImpl(daoProvider.users, daoProvider.spaces, ServiceConfig("")))

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
        expect(res.body.contains("Invalid email: johndoe.com")).toBe(true)
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