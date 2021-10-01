package pimonitor.screens

import bitframe.authentication.signin.SignInCredentials
import com.codeborne.selenide.Selectors.withText
import pimonitor.screens.authentication.SignInScreen
import pimonitor.utils.isVisible
import com.codeborne.selenide.Selenide.`$` as S

class SignInScreenWeb : SignInScreen {
    private val email by lazy { S("#email") }
    private val pass by lazy { S("#pass") }

    override suspend fun isVisible(): Boolean = email.isVisible() && pass.isVisible()

    override suspend fun signIn(credentials: SignInCredentials) {
        email.sendKeys(credentials.alias)
        pass.sendKeys(credentials.password)
        pass.pressEnter()
    }

    override suspend fun isShowingInvalidCredentials(): Boolean {
        return S(withText("not found")).isVisible()
    }
}