package pimonitor.screens

import com.codeborne.selenide.Selectors.withText
import pimonitor.screens.dashboard.BusinessesScreen
import pimonitor.screens.dashboard.BusinessesScreenWeb
import pimonitor.screens.dashboard.DashboardScreen
import pimonitor.utils.isVisible
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