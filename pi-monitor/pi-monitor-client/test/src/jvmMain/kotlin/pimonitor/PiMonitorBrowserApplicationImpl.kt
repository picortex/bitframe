package pimonitor

import bitframe.screens.SignInScreenWeb
import bitframe.screens.authentication.SignInScreen
import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.Assertions
import org.openqa.selenium.By
import pimonitor.screens.HomeScreenWeb
import pimonitor.screens.PiMonitorLandingScreen
import pimonitor.screens.SignUpScreenWeb
import pimonitor.screens.authentication.SignUpScreen
import pimonitor.utils.isVisible
import com.codeborne.selenide.Selenide.`$` as S

class PiMonitorBrowserApplicationImpl(
    private val url: String,
    private val landingScreen: PiMonitorLandingScreen = HomeScreenWeb(),
    private val signInScreen: SignInScreen = SignInScreenWeb(),
    private val signUpScreen: SignUpScreen = SignUpScreenWeb()
) : PiMonitorBrowserApplication {

    override suspend fun openLandingScreen(): PiMonitorLandingScreen {
        open(url)
        return landingScreen
    }

    override suspend fun openSignUpScreen(): SignUpScreen {
        return openLandingScreen().clickSignUpButton()
    }

    override suspend fun expectUserToBeLoggedIn() {
        val el = S(By.xpath("/html/body/div/div/div[1]/div/div[1]/div[2]/div/div/div/div/div[1]"))
        Assertions.assertTrue(el.isVisible()) {
            "Expected User to be logged in but was not"
        }
    }

    override suspend fun expectUserToNotBeLoggedIn() {
        Assertions.assertTrue(
            signInScreen.isShowingInvalidCredentials() || landingScreen.isVisible()
        ) {
            "Expected User to not be logged in but was logged in"
        }
    }
}