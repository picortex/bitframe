package acceptance.authentication

import acceptance.utils.IntegrationTest
import expect.expect
import kotlinx.coroutines.runTest
import kotlinx.datetime.Clock
import later.await
import org.junit.jupiter.api.TestInstance
import org.testcontainers.junit.jupiter.Testcontainers
import pimonitor.authentication.signup.IndividualRegistrationParams
import pimonitor.PiMonitorService
import pimonitor.ktor.PiMonitorServiceKtor
import kotlin.test.Test

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignUpServiceKtorServiceTest : IntegrationTest() {

    private val service: PiMonitorService by lazy { PiMonitorServiceKtor(config) }

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