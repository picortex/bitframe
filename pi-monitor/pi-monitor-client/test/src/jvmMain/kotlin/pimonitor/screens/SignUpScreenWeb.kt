package pimonitor.screens

import com.codeborne.selenide.Selectors.byAttribute
import com.codeborne.selenide.Selectors.withText
import org.openqa.selenium.By
import pimonitor.authentication.signup.IndividualRegistrationParams
import pimonitor.authentication.signup.MonitorBusinessParams
import pimonitor.screens.authentication.SignUpScreen
import pimonitor.utils.isVisible
import kotlin.test.assertTrue
import com.codeborne.selenide.Selenide.`$` as S

class SignUpScreenWeb : SignUpScreen {
    private val nameInput = S(By.name("name"))
    private val emailInput = S(By.name("email"))
    private val passwordInput = S(By.name("password"))
    private val nextOrSubmitButton = S(byAttribute("type", "submit"))

    override suspend fun signUpIndividuallyAs(person: IndividualRegistrationParams) {
        S(withText("Individual")).click()
        nameInput.sendKeys(person.name)
        emailInput.sendKeys(person.email)
        passwordInput.sendKeys(person.password)

        nextOrSubmitButton.click()
    }

    override suspend fun signUpAs(person: IndividualRegistrationParams, representing: MonitorBusinessParams) {
        S(withText("Organisation")).click()
        nameInput.sendKeys(representing.name)
        emailInput.sendKeys(representing.email)
        nextOrSubmitButton.click()
        nameInput.sendKeys(person.name)
        emailInput.sendKeys(person.email)
        passwordInput.sendKeys(person.password)
        nextOrSubmitButton.click()
    }

    private suspend fun expectUserToBeRegistered(waitCounts: Int) {
        assertTrue(
            S(withText("Registration completed successfully")).isVisible(),
            "Expected User to be Registered but was not"
        )
    }

    override suspend fun expectUserToBeRegistered() {
        expectUserToBeRegistered(0)
    }

    override suspend fun isVisible(): Boolean = S(withText("Register As")).isVisible()
}