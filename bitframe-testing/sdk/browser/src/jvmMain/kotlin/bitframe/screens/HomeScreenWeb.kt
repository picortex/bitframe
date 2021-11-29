package bitframe.screens

import bitframe.screens.authentication.SignInScreen
import bitframe.utils.isVisible
import com.codeborne.selenide.Selectors.withText
import org.openqa.selenium.By
import com.codeborne.selenide.Selenide.`$` as S

open class HomeScreenWeb : LandingScreen {
    override suspend fun isVisible() = S(withText("Welcome to Bitframe")).isVisible()

    override fun clickSignInButton(): SignInScreen {
        S(By.xpath("/html/body/div/div/div/div/button[2]")).click()
        return SignInScreenWeb()
    }
}