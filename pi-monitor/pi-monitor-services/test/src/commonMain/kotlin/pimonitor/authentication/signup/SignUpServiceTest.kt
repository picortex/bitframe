package pimonitor.authentication.signup

import expect.expect
import kotlinx.coroutines.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.PiMonitorService
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
        val email = "andylamax$stamp@programmer.net"
        val individual: SignUpParams = SignUpParams.Individual(
            name = "Anderson Lameck - $stamp",
            email = email,
            password = "1234"
        )

        // When they sign up
        val result = service.signUp.signUp(individual).await()

        // They should be able to sign in
        val singInConundrum = service.signIn.signIn(individual.toCredentials()).await()
        expect(singInConundrum.spaces).toBeOfSize(1)

        // Their user tag should equal their username
        expect(email).toBe(result.user.contacts.mapEachToString().first())
    }
}