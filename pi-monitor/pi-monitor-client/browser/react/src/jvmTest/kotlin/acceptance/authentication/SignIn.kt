package acceptance.authentication

import acceptance.utils.URL_UNDER_TEST
import bitframe.authentication.LoginCredentials
import expect.expect
import org.junit.jupiter.api.Test
import pimonitor.WebApplication
import pimonitor.screens.api.toBeVisible
import pimonitor.test

class SignIn {
    private val application = WebApplication(URL_UNDER_TEST)

    @Test
    fun should_be_able_to_just_open_the_application() = application.test {
        val landingScreen = openLandingScreen()
        expect(landingScreen).toBeVisible()
    }

    @Test
    fun should_give_a_user_with_valid_credentials_access() = application.test {
        val signInScreen = openLandingScreen().clickSignInButton()
        signInScreen.loginWith(LoginCredentials("user1", "pass1"))
        expectUserToBeLoggedIn()
    }

    @Test
    fun should_not_give_a_user_with_valid_credentials_access() = application.test {
        val signInScreen = openLandingScreen().clickSignInButton()
        expect(signInScreen).toBeVisible()
        signInScreen.loginWith(LoginCredentials("username", "password"))
        expectUserToNotBeLoggedIn()
    }
}