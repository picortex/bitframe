package pimonitor.screens.dashboard

import com.codeborne.selenide.Selectors.withText
import com.codeborne.selenide.Selenide.`$` as S

class BusinessesScreenWeb : BusinessesScreen {

    override fun clickCreateButton(): AddBusinessForm {
        val createButton = S(withText("Create"))
        createButton.click()
        return AddBusinessFormWeb()
    }
}