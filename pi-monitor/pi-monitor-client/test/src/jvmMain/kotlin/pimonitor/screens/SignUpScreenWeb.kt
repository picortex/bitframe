package pimonitor.screens

import com.codeborne.selenide.Selectors.byAttribute
import com.codeborne.selenide.Selectors.withText
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By
import pimonitor.monitors.SignUpParams
import pimonitor.screens.authentication.SignUpScreen
import pimonitor.utils.isVisible
import kotlin.properties.ReadOnlyProperty
import com.codeborne.selenide.Selenide.`$` as S

class SignUpScreenWeb : SignUpScreen {
    val select = S(By.name("registrationType"))
    private val nextOrSubmitButton = S(byAttribute("type", "submit"))

    private fun signUp(params: SignUpParams.Individual) {
        val name by name()
        val email by name()
        val password by name()

        name.sendKeys(params.name)
        email.sendKeys(params.email)
        password.sendKeys(params.password)

        nextOrSubmitButton.click()
    }

    fun name() = ReadOnlyProperty<Any?, SelenideElement> { thisRef, property ->
        S(By.name(property.name))
    }

    private fun signUp(params: SignUpParams.Business) {
        select.selectOption(1)

        val businessName by name()
        val individualName by name()
        val individualEmail by name()
        val password by name()

        businessName.sendKeys(params.businessName)
        individualName.sendKeys(params.individualName)
        individualEmail.sendKeys(params.individualEmail)
        password.sendKeys(params.password)
        nextOrSubmitButton.click()
    }

    override suspend fun signUp(with: SignUpParams) = when (with) {
        is SignUpParams.Business -> signUp(with)
        is SignUpParams.Individual -> signUp(with)
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