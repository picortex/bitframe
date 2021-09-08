package pimonitor

import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.Assertions
import org.openqa.selenium.By
import pimonitor.screens.HomeScreen
import pimonitor.screens.SignInScreen
import pimonitor.screens.LandingScreen
import pimonitor.utils.isVisible
import com.codeborne.selenide.Selenide.`$` as S

class WebApplication(
    private val url: String,
    private val landingScreen: LandingScreen = HomeScreen(),
    private val signInScreen: SignInScreen = SignInScreen()
) : Application {

    fun openLandingScreen(): LandingScreen {
        open(url)
        return landingScreen
    }

    override fun expectUserToBeLoggedIn() {
        val el = S(By.xpath("/html/body/div/div/div[1]/div/div[1]/div[2]/div/div/div/div/div[1]"))
        Assertions.assertTrue(el.isVisible()) {
            "Expected User to be logged in but was not"
        }
    }

    override fun expectUserToNotBeLoggedIn() {
        Assertions.assertTrue(
            signInScreen.isShowingInvalidCredentials() || landingScreen.isVisible()
        ) {
            "Expected User to not be logged in but was logged in"
        }
    }
}