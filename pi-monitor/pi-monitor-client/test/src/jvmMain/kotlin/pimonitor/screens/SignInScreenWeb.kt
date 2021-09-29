package pimonitor.screens

import bitframe.authentication.signin.LoginCredentials
import com.codeborne.selenide.Condition.text
import org.openqa.selenium.By
import pimonitor.screens.authentication.SignInScreen
import pimonitor.utils.isVisible
import com.codeborne.selenide.Selenide.`$` as S

class SignInScreenWeb : SignInScreen {
    private val email by lazy { S("#email") }
    private val pass by lazy { S("#pass") }

    override suspend fun isVisible(): Boolean = email.isVisible() && pass.isVisible()

    override suspend fun loginWith(credentials: LoginCredentials) {
        email.sendKeys(credentials.alias)
        pass.sendKeys(credentials.password)
        pass.pressEnter()
    }

    override suspend fun isShowingInvalidCredentials(): Boolean {
        val el = S(By.xpath("/html/body/div/div/div/div/div/div/div[2]"))
        return try {
            el.shouldHave(text("Invalid Login Credentials"))
            true
        } catch (err: Throwable) {
            false
        }
    }
}