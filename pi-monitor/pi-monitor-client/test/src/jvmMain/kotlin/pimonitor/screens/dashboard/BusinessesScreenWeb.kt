package pimonitor.screens.dashboard

import com.codeborne.selenide.Selectors.withText
import pimonitor.utils.isVisible
import pimonitor.screens.dashboard.AddBusinessForm
import pimonitor.screens.dashboard.BusinessesScreen
import com.codeborne.selenide.Selenide.`$` as S

class BusinessesScreenWeb : BusinessesScreen {

    override fun clickCreateButton(): AddBusinessForm {
        val createButton = S(withText("Create"))
        createButton.click()
        return AddBusinessFormWeb()
    }

    override fun expectToHaveBusinessWithName(businessName: String) {
        S(withText(businessName)).isVisible()
    }
}