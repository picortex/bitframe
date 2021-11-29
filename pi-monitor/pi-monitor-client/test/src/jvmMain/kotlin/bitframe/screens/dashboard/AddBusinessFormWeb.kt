package bitframe.screens.dashboard

import bitframe.monitored.CreateMonitoredBusinessParams
import bitframe.utils.name
import bitframe.utils.submit

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