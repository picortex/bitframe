package integration.ktor.authentication

import expect.expect
import integration.ktor.utils.CONFIGURATION_UNDER_TEST
import kotlinx.coroutines.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.IndividualRegistrationParams
import pimonitor.ktor.PiMonitorServiceKtor
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
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

        val res = service.signUp.registerIndividuallyAs(individual).await()
        println(res)

        val conundrum = service.signIn.signIn(individual.credentials()).await()

        expect(individual.name).toBe(conundrum.user.username)

        expect(conundrum.accounts).toBeOfSize(1)
    }
}