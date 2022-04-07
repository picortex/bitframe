package pimonitor.client.business.info

import pimonitor.core.business.info.params.BusinessInfoRawFormParams

sealed class BusinessInfoIntent {
    object ExitDialog : BusinessInfoIntent()

    data class LoadInfo(val businessId: String) : BusinessInfoIntent()

    data class ShowEditForm(val businessId: String) : BusinessInfoIntent()

    data class SendEditForm(val params: BusinessInfoRawFormParams) : BusinessInfoIntent()
}
