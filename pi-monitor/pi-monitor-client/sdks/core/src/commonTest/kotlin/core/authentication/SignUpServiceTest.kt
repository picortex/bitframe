package core.authentication

import expect.expect
import kotlinx.coroutines.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.IndividualRegistrationParams
import testing.IntegrationTest
import testing.annotations.Lifecycle
import testing.annotations.TestInstance
import testing.annotations.Testcontainers
import kotlin.test.Test

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
abstract class SignUpServiceTest : IntegrationTest() {

    abstract val service: PiMonitorService

    @Test
    fun should_register_an_individual() = runTest {
        // At
        val stamp = Clock.System.now().epochSeconds

        // Given an individual with
        val individual = IndividualRegistrationParams(
            name = "Anderson Lameck - $stamp",
            email = "andylamax$stamp@programmer.net",
            password = "1234"
        )

        // When they sign up
        val singUpConundrum = service.signUp.registerIndividuallyAs(individual).await()
        expect(singUpConundrum.spaces).toBeOfSize(1)

        // They should be able to sign in
        val singInConundrum = service.signIn.signIn(individual.credentials()).await()
        expect(singInConundrum.spaces).toBeOfSize(1)

        // the results return should be the same
        expect(singUpConundrum).toBe(singInConundrum)

        // Their user tag should equal their username
        expect(individual.name).toBe(singUpConundrum.user.tag)
    }
}