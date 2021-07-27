package pimonitor.pages

import com.codeborne.selenide.Selenide.open
import bitframe.authentication.LoginCredentials
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import org.openqa.selenium.By
import com.codeborne.selenide.Selenide.`$` as S

class BrowserLoginPage(val url: String) : LoginPage {
    override suspend fun loginWith(credentials: LoginCredentials) {
        open(url)
        S("#username").sendKeys(credentials.username)
        S("#pass").sendKeys(credentials.password)
        S("#pass").pressEnter()
    }

    override suspend fun expectUserToBeLoggedIn() {
        val dialog = S(By.xpath("/html/body/div/div/div/div/div/div[2]"))
        dialog.shouldHave(text("Logged in successfully"))
    }

    override suspend fun expectUserToNotBeLoggedIn() {
        S("#accordion-title").shouldBe(visible)
    }
}