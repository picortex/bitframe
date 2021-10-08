package pimonitor.screens

import com.codeborne.selenide.Selectors.byAttribute
import com.codeborne.selenide.Selectors.withText
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By
import pimonitor.authentication.signup.IndividualRegistrationParams
import pimonitor.authentication.signup.MonitorBusinessParams
import pimonitor.screens.authentication.SignUpScreen
import pimonitor.utils.isVisible
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.test.assertTrue
import com.codeborne.selenide.Selenide.`$` as S

class SignUpScreenWeb : SignUpScreen {
    val select = S(By.name("registrationType"))
    private val passwordInput = S(By.name("password"))
    private val nextOrSubmitButton = S(byAttribute("type", "submit"))

    override suspend fun signUpIndividuallyAs(person: IndividualRegistrationParams) {
        val nameInput = S(By.name("name"))
        val emailInput = S(By.name("email"))

        nameInput.sendKeys(person.name)
        emailInput.sendKeys(person.email)
        passwordInput.sendKeys(person.password)

        nextOrSubmitButton.click()
    }

    fun name() = ReadOnlyProperty<Any?, SelenideElement> { thisRef, property ->
        S(By.name(property.name))
    }

    override suspend fun signUpAs(person: IndividualRegistrationParams, representing: MonitorBusinessParams) {
        select.selectOption(1)

        val businessName by name()
        val individualName by name()
        val individualEmail by name()
        val password by name()

        businessName.sendKeys(representing.name)
        individualName.sendKeys(person.name)
        individualEmail.sendKeys(person.email)
        password.sendKeys(person.password)
        nextOrSubmitButton.click()
    }

    private suspend fun expectUserToBeRegistered(waitCounts: Int) {
        // TODO
//
//        assertTrue(
//            S(withText("Registration completed successfully")).isVisible(), "Expected User to be Registered but was not"
//        )
    }

    override suspend fun expectUserToBeRegistered() {
        expectUserToBeRegistered(0)
    }

    override suspend fun isVisible(): Boolean = S(withText("Register As")).isVisible()
}