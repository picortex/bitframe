@file:Suppress("ClassName")

package acceptance.authentication

import acceptance.utils.PiMonitorAcceptanceTest
import bitframe.authentication.signin.SignInCredentials
import expect.expect
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.testcontainers.junit.jupiter.Testcontainers
import bitframe.screens.api.toBeVisible
import bitframe.test

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignIn : PiMonitorAcceptanceTest() {

    @Nested
    inner class `When users with valid credentials attempts to login` {
        @Test
        fun then_users_should_be_logged_in() = application.test {
            val signInScreen = openLandingScreen().clickSignInButton()
            signInScreen.signIn(SignInCredentials("user1@test.com", "pass1"))
            expectUserToBeLoggedIn()
        }
    }

    @Nested
    inner class `When users with invalid credentials attempts to login` {
        @ParameterizedTest
        @CsvSource("username,password", "user01,password", "user02,password")
        fun they_should_not_succeed(username: String, password: String) = application.test {
            val signInScreen = openLandingScreen().clickSignInButton()
            expect(signInScreen).toBeVisible()
            signInScreen.signIn(SignInCredentials(username, password))
            expectUserToNotBeLoggedIn()
        }
    }
}