package bitframe.screens

import com.codeborne.selenide.Selectors.withText
import bitframe.screens.dashboard.BusinessesScreen
import bitframe.screens.dashboard.BusinessesScreenWeb
import bitframe.screens.dashboard.DashboardScreen
import bitframe.utils.isVisible
import com.codeborne.selenide.Selenide.`$` as S

class DashboardScreenWeb : DashboardScreen {
    override suspend fun isVisible(): Boolean {
        return S(withText("Dashboard")).isVisible()
    }

    override suspend fun selectBusinesses(): BusinessesScreen {
        S(withText("Business")).click()
        return BusinessesScreenWeb()
    }
}