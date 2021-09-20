package pimonitor

import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.Assertions
import org.openqa.selenium.By
import pimonitor.screens.HomeScreenWeb
import pimonitor.screens.SignInScreenWeb
import pimonitor.screens.LandingScreen
import pimonitor.screens.SignUpScreenWeb
import pimonitor.screens.authentication.SignInScreen
import pimonitor.screens.authentication.SignUpScreen
import pimonitor.utils.isVisible
import com.codeborne.selenide.Selenide.`$` as S

class WebApplication(
    private val url: String,
    private val landingScreen: LandingScreen = HomeScreenWeb(),
    private val signInScreen: SignInScreen = SignInScreenWeb(),
    private val signUpScreen: SignUpScreen = SignUpScreenWeb()
) : Application {

    override suspend fun openLandingScreen(): LandingScreen {
        open(url)
        return landingScreen
    }

    override suspend fun openSignUpScreen(): SignUpScreen {
//        open("$url/authentication/sign-up/")
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