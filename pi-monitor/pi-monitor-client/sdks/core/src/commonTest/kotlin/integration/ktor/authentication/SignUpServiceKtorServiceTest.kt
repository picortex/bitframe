package integration.ktor.authentication

import expect.expect
import integration.ktor.utils.CONFIGURATION_UNDER_TEST
import kotlinx.coroutines.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.IndividualRegistrationParams
import pimonitor.ktor.PiMonitorServiceKtor
import pimonitor.toPerson
import kotlin.test.Test

class SignUpServiceKtorServiceTest {

    private val service = PiMonitorServiceKtor(CONFIGURATION_UNDER_TEST)

    @Test
    fun should_register_an_individual() = runTest {
        val stamp = Clock.System.now().epochSeconds

        val individual = IndividualRegistrationParams(
            name = "Anderson Lameck - $stamp",
            email = "andylamax$stamp@programmer.net",
            password = "1234"
        )

        service.signUp.registerIndividuallyAs(individual).await()

        val conundrum = service.signIn.loginWith(individual.credentials()).await()
        expect(individual.name).toBe(conundrum.user.username)
        expect(conundrum.accounts).toBeOfSize(1)
    }
}