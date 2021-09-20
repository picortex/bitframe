@file:JsExport

package pimonitor.evaluation.business

import pimonitor.evaulation.business.BusinessService

class BusinessScope(service: BusinessService) {
    val viewModel = BusinessViewModel(service)

    fun loadBusinessIntent() = BusinessIntent.LoadBusinesses

    val loadBusiness: () -> Unit = { viewModel.post(loadBusinessIntent()) }
}