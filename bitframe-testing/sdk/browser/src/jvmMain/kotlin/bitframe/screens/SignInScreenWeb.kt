package bitframe.screens

import bitframe.authentication.signin.SignInCredentials
import com.codeborne.selenide.Selectors.withText
import org.openqa.selenium.By
import bitframe.screens.authentication.SignInScreen
import bitframe.screens.dashboard.DashboardScreen
import bitframe.utils.isVisible
import com.codeborne.selenide.Selenide.`$` as S

open class SignInScreenWeb : SignInScreen {
    private val email by lazy { S(By.name("email")) }
    private val pass by lazy { S(By.name("password")) }

    override suspend fun isVisible(): Boolean = email.isVisible() && pass.isVisible()

    override suspend fun signIn(credentials: SignInCredentials): DashboardScreen {
        email.sendKeys(credentials.alias)
        pass.sendKeys(credentials.password)
        pass.pressEnter()
        return DashboardScreenWeb()
    }

    override suspend fun isShowingInvalidCredentials(): Boolean {
        return S(withText("not found")).isVisible()
    }
}