package pimonitor.screens.dashboard

interface BusinessesScreen {
    fun clickCreateButton(): AddBusinessForm
    fun expectToHaveBusinessWithName(businessName: String)
}