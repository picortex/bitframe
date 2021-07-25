package acceptance

import acceptance.utils.URL
import org.junit.jupiter.api.Test
import pimonitor.authentication.Credentials
import pimonitor.pages.BrowserLoginPage
import pimonitor.pages.LoginPage
import pimonitor.pages.test

class Authentication {
    private val login: LoginPage = BrowserLoginPage(URL)

    @Test
    fun should_give_a_user_with_valid_credentials_access() = login.test {
        loginWith(Credentials("username", "password"))
        expectUserToBeLoggedIn()
    }

    @Test
    fun should_not_give_a_user_with_valid_credentials_access() = login.test {
        loginWith(Credentials("username", "password"))
        expectUserToNotBeLoggedIn()
    }
}