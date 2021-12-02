package pimonitor.screens.dashboard

import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.utils.name
import pimonitor.utils.submit
import pimonitor.screens.dashboard.AddBusinessForm

class AddBusinessFormWeb : AddBusinessForm {
    val businessName by name()
    val contactName by name()
    val contactEmail by name()

    val submit by submit()

    override suspend fun enter(details: CreateMonitoredBusinessParams) {
        businessName.sendKeys(details.businessName)
        contactName.sendKeys(details.contactName)
        contactEmail.sendKeys(details.contactEmail)
    }

    override suspend fun submitByPressingEnter() {
        contactEmail.pressEnter()
    }

    override suspend fun submitByClicking() {
        submit.click()
    }
}