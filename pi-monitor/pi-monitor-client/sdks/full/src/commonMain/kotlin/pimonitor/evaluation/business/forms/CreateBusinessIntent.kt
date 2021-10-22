package pimonitor.evaluation.business.forms

import pimonitor.monitored.CreateMonitoredBusinessParams

sealed class CreateBusinessIntent {
    data class ShowForm(val inviteId: String?) : CreateBusinessIntent()
    data class SubmitForm(val params: CreateMonitoredBusinessParams) : CreateBusinessIntent()
}
