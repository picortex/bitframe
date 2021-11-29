package bitframe.screens

import com.codeborne.selenide.Selectors.withText
import org.openqa.selenium.By
import bitframe.authentication.signup.SignUpParams
import bitframe.screens.authentication.SignUpProcess
import bitframe.screens.authentication.SignUpScreen
import bitframe.utils.isVisible
import bitframe.utils.name
import bitframe.utils.submit
import com.codeborne.selenide.Selenide.`$` as S

class SignUpScreenWeb : SignUpScreen {
    val select = S(By.name("registrationType"))
    private val nextOrSubmitButton by submit()

    private fun signUp(params: SignUpParams.Individual): SignUpProcess {
        val name by name()
        val email by name()
        val password by name()

        name.sendKeys(params.name)
        email.sendKeys(params.email)
        password.sendKeys(params.password)

        nextOrSubmitButton.click()
        return SignUpProcessWeb()
    }

    private fun signUp(params: SignUpParams.Business): SignUpProcess {
        select.selectOption(0)

        val businessName by name()
        val individualName by name()
        val individualEmail by name()
        val password by name()

        businessName.sendKeys(params.businessName)
        individualName.sendKeys(params.individualName)
        individualEmail.sendKeys(params.individualEmail)
        password.sendKeys(params.password)
        nextOrSubmitButton.click()
        return SignUpProcessWeb()
    }

    override suspend fun signUp(with: SignUpParams) = when (with) {
        is SignUpParams.Business -> signUp(with)
        is SignUpParams.Individual -> signUp(with)
    }

    override suspend fun expectToBeSigningUp() {
        S(withText("Creating your account, please wait . . .")).isVisible()
        S(withText("Success. signing you in . . .")).isVisible()
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