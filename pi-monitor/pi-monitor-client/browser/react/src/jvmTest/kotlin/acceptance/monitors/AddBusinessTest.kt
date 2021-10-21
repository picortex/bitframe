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
        val details = CreateMonitoredBusinessParams(
            businessName = "PiCortex",
            contactName = "Mohammed Majapa",
            contactEmail = "mmajapa@gmail.com"
        )
        val businesses = dashboard.selectBusinesses()
        businesses.clickCreateButton().apply {
            enter(details)
            submitByPressingEnter()
        }
    }
}