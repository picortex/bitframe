package pimonitor.pages

import com.codeborne.selenide.Selenide.open
import bitframe.authentication.Credentials
import com.codeborne.selenide.Selenide.`$` as S

class BrowserLoginPage(val url: String) : LoginPage {
    override suspend fun loginWith(credentials: Credentials) {
        open(url)
        S("#username").sendKeys(credentials.username)
        S("#pass").sendKeys(credentials.password)
    }

    override suspend fun expectUserToBeLoggedIn() {
        //
    }

    override suspend fun expectUserToNotBeLoggedIn() {
        //
    }
}