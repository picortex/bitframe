package bitframe.authentication.signup

import expect.expect
import kotlinx.coroutines.runTest
import kotlinx.datetime.Clock
import pimonitor.testing.PiMonitorIntegrationTest
import pimonitor.testing.annotations.Lifecycle
import pimonitor.testing.annotations.TestInstance
import pimonitor.testing.annotations.Testcontainers
import kotlin.test.Ignore
import kotlin.test.Test

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
abstract class SignUpServiceTest : PiMonitorIntegrationTest() {

    abstract val service: PiMonitorService

    @Test
    @Ignore // Fix after we have successfully found a way to initialize configurations/test data
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