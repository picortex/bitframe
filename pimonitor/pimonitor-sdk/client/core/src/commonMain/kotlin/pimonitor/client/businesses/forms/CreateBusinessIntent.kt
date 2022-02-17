package pimonitor.client.businesses.forms

import pimonitor.core.businesses.params.RawCreateBusinessParams

sealed class CreateBusinessIntent {
    data class ShowForm(val inviteId: String?) : CreateBusinessIntent()
    data class SubmitForm(val params: RawCreateBusinessParams) : CreateBusinessIntent()
}
