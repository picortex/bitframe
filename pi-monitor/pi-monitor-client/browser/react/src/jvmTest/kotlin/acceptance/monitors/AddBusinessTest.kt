package acceptance.monitors

import acceptance.utils.AcceptanceTest
import kotlinx.coroutines.delay
import org.junit.jupiter.api.TestInstance
import org.testcontainers.junit.jupiter.Testcontainers
import pimonitor.authentication.signup.SignUpParams
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.test
import kotlin.test.Test

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddBusinessTest : AcceptanceTest() {
    @Test
    fun should_be_able_to_add_a_business() = application.test {
        val params = SignUpParams.Individual(
            name = "Jane Doe", email = "jane@doe.com", password = "janedoe"
        )
        val dashboard = openSignUpScreen().signUp(params).expectToBeSigningUp()
        val businesses = dashboard.selectBusinesses()
        for (i in 1..5) {
            val details = CreateMonitoredBusinessParams(
                businessName = "PiCortex - $i",
                contactName = "Mohammed Majapa - $i",
                contactEmail = "mmajapa$i@gmail.com"
            )
            businesses.clickCreateButton().apply {
                enter(details)
                submitByPressingEnter()
            }
            businesses.expectToHaveBusinessWithName(details.businessName)
        }
    }
}