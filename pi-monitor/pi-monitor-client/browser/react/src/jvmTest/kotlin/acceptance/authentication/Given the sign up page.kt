@file:Suppress("ClassName")

package acceptance.authentication

import acceptance.utils.AcceptanceTest
import acceptance.utils.URL_UNDER_TEST
import contacts.Email
import expect.expect
import org.junit.jupiter.api.TestInstance
import org.testcontainers.junit.jupiter.Testcontainers
import pimonitor.Monitor
import pimonitor.MonitorParams
import pimonitor.WebApplication
import pimonitor.screens.api.toBeVisible
import pimonitor.test
import kotlin.test.Test

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class `Given the sign up page` : AcceptanceTest() {
    @Test
    fun should_register_a_new_user() = application.test {
        val landingScreen = openLandingScreen()
        expect(landingScreen).toBeVisible()
        val signUpScreen = landingScreen.clickSignUpButton()
        val person = MonitorParams(name = "John Doe", email = "john.doe@johndoeinc.com")
        val business = MonitorParams(name = "John Doe Inc.", email = "support@johndoeinc.com")
        signUpScreen.signUpAs(person, representing = business)
        signUpScreen.expectUserToBeRegistered()
    }
}