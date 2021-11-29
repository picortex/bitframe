package bitframe.evaluation.businesses.forms

import bitframe.monitored.CreateMonitoredBusinessParams

sealed class CreateBusinessIntent {
    data class ShowForm(val inviteId: String?) : CreateBusinessIntent()
    data class SubmitForm(val params: CreateMonitoredBusinessParams) : CreateBusinessIntent()
}
