package pimonitor.screens

import bitframe.screens.SignInScreenWeb
import bitframe.screens.authentication.SignInScreen
import com.codeborne.selenide.Selectors.withText
import org.openqa.selenium.By
import pimonitor.screens.authentication.SignUpScreen
import pimonitor.utils.isVisible
import com.codeborne.selenide.Selenide.`$` as S

class HomeScreenWeb : PiMonitorLandingScreen {
    override suspend fun isVisible() = S(withText("Welcome to Bitframe")).isVisible()

    override fun clickSignInButton(): SignInScreen {
        S(By.xpath("/html/body/div/div/div/div/button[2]")).click()
        return SignInScreenWeb()
    }

    override fun clickSignUpButton(): SignUpScreen {
        S(By.xpath("/html/body/div/div/div/div/button[1]")).click()
        return SignUpScreenWeb()
    }
}