@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.business

import pimonitor.evaulation.business.BusinessService
import viewmodel.ViewModel
import pimonitor.evaluation.business.BusinessesIntent as Intent
import pimonitor.evaluation.business.BusinessesState as State

class BusinessesScope(service: BusinessService) {
    val viewModel: ViewModel<Intent, State> = BusinessViewModel(service)

    val loadBusiness: () -> Unit = { viewModel.post(Intent.LoadBusinesses) }
}