package pimonitor.screens

import kotlinx.coroutines.delay
import org.openqa.selenium.By
import pimonitor.MonitorParams
import pimonitor.screens.authentication.SignUpScreen
import kotlin.test.assertTrue
import com.codeborne.selenide.Selenide.`$` as S

class SignUpScreen : SignUpScreen {
    override suspend fun signUpAs(person: MonitorParams, representing: MonitorParams) {
        val nameInput = S(By.xpath("/html/body/div/div/div/form/div[1]/input"))
        nameInput.sendKeys(representing.name)
        val emailInput = S(By.xpath("/html/body/div/div/div/form/div[2]/input"))
        emailInput.sendKeys(representing.email)
        val nextOrSubmitButton = S(By.xpath("/html/body/div/div/div/form/div[3]/button[2]"))
        nextOrSubmitButton.click()
        nameInput.sendKeys(person.name)
        emailInput.sendKeys(person.email)
        nextOrSubmitButton.click()
    }

    private suspend fun expectUserToBeRegistered(waitCounts: Int) {
        val dialog = S(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]"))
        if (waitCounts < 5 && dialog.innerText().contains("Please wait")) {
            delay(1000)
            expectUserToBeRegistered(waitCounts + 1)
        }
        assertTrue(
            dialog.innerText().contains("Your registration completed successfully"),
            "Expected User to be Registered but was not"
        )
    }

    override suspend fun expectUserToBeRegistered() {
        expectUserToBeRegistered(0)
    }

    override fun isVisible(): Boolean {
        val heading = S(By.xpath("/html/body/div/div/div/form/h2"))
        return heading.innerText().contains("Enter Business Info")
    }
}