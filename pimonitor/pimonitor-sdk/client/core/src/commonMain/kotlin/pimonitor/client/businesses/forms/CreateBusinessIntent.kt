package pimonitor.client.businesses.forms

import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams

sealed class CreateBusinessIntent {
    data class SubmitForm(val params: CreateMonitoredBusinessRawParams) : CreateBusinessIntent()
}
