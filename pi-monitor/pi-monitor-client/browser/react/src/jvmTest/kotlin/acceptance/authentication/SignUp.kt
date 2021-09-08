package acceptance.authentication

import acceptance.utils.URL_UNDER_TEST
import contacts.Email
import expect.expect
import pimonitor.Monitor
import pimonitor.WebApplication
import pimonitor.screens.api.toBeVisible
import pimonitor.test
import kotlin.test.Test

class SignUp {
    private val application = WebApplication(URL_UNDER_TEST)

    @Test
    fun should_sign_register_a_new_user() = application.test {
        val landingScreen = openLandingScreen()
        expect(landingScreen).toBeVisible()
        val signUpScreen = landingScreen.clickSignUpButton()
        val person = Monitor.Person(
            name = "John Doe", email = Email("john.doe@johndoeinc.com")
        )
        val business = Monitor.Business(
            name = "John Doe Inc.", email = Email("support@johndoeinc.com"), logo = null
        )
        signUpScreen.signUpAs(person, representing = business)
    }
}