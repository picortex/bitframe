package pimonitor.evaluation.business.forms

sealed class CreateBusinessIntent {
    data class ShowForm(val inviteId: String?) : CreateBusinessIntent()
}
