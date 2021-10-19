package pimonitor.screens

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selectors.withText
import pimonitor.screens.authentication.SignUpProcess
import pimonitor.screens.dashboard.DashboardScreen
import pimonitor.utils.isVisible
import com.codeborne.selenide.Selenide.`$` as S

class SignUpProcessWeb : SignUpProcess {
    override fun expectToBeSigningUp(): DashboardScreen {
        S(withText("Creating your account, please wait . . .")).isVisible()
        S(withText("Success. Signing you in, please wait . . .")).isVisible()
        S(withText("Dashboard")).isVisible()
        return DashboardScreenWeb()
    }
}