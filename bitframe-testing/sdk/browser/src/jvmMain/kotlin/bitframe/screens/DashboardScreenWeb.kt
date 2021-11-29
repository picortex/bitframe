package bitframe.screens

import com.codeborne.selenide.Selectors.withText
import bitframe.screens.dashboard.DashboardScreen
import bitframe.utils.isVisible
import com.codeborne.selenide.Selenide.`$` as S

class DashboardScreenWeb : DashboardScreen {
    override suspend fun isVisible(): Boolean {
        return S(withText("Dashboard")).isVisible()
    }
}