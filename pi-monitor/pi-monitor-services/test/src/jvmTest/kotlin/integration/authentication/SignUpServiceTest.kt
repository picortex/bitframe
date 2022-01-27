package integration.authentication

import pimonitor.authentication.signup.SignUpParams
import pimonitor.authentication.signup.toCredentials
import bitframe.testing.annotations.Lifecycle
import bitframe.testing.annotations.TestInstance
import bitframe.testing.annotations.Testcontainers
import expect.expect
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.testing.PiMonitorIntegrationTest
import kotlin.test.Ignore
import kotlin.test.Test

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
class SignUpServiceTest : PiMonitorIntegrationTest() {

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
        val result = piMonitorService.signUp.signUp(individual).await()

        // They should be able to sign in
        val singInConundrum = piMonitorService.signIn.signIn(individual.toCredentials()).await()
        expect(singInConundrum.spaces).toBeOfSize(1)

        // Their user tag should equal their username
        expect(email).toBe(result.user.contacts.mapEachToString().first())
    }
}