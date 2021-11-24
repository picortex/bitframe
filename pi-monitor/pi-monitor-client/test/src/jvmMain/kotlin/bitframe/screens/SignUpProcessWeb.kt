package bitframe.screens

import com.codeborne.selenide.Selectors.withText
import bitframe.screens.authentication.SignUpProcess
import bitframe.screens.dashboard.DashboardScreen
import bitframe.utils.isVisible
import com.codeborne.selenide.Selenide.`$` as S

class SignUpProcessWeb : SignUpProcess {
    override fun expectToBeSigningUp(): DashboardScreen {
        S(withText("Creating your account, please wait . . .")).isVisible()
        S(withText("Success. Signing you in, please wait . . .")).isVisible()
        S(withText("Dashboard")).isVisible()
        return DashboardScreenWeb()
    }
}