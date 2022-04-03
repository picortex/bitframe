package pimonitor.client.business

sealed class BusinessDetailsIntent {
    data class LoadBusiness(val businessId: String) : BusinessDetailsIntent()
}