package pimonitor.client.businesses.forms

import pimonitor.core.monitored.CreateMonitoredBusinessParams

sealed class CreateBusinessIntent {
    data class ShowForm(val inviteId: String?) : CreateBusinessIntent()
    data class SubmitForm(val params: CreateMonitoredBusinessParams) : CreateBusinessIntent()
}
