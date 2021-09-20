package pimonitor.screens

import com.codeborne.selenide.Condition.text
import org.openqa.selenium.By
import pimonitor.screens.authentication.SignInScreen
import pimonitor.screens.authentication.SignUpScreen
import com.codeborne.selenide.Selenide.`$` as S

class HomeScreenWeb : LandingScreen {
    override suspend fun isVisible(): Boolean = try {
        S(By.xpath("//*[@id=\"root\"]/div")).shouldHave(text("Welcome to bitframe"))
        true
    } catch (err: Throwable) {
        false
    }

    override fun clickSignInButton(): SignInScreen {
        S(By.xpath("/html/body/div/div/div/div/button[2]")).click()
        return SignInScreenWeb()
    }

    override fun clickSignUpButton(): SignUpScreen {
        S(By.xpath("/html/body/div/div/div/div/button[1]")).click()
        return SignUpScreenWeb()
    }
}