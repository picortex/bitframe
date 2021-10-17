@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.business

import pimonitor.PiMonitorService
import pimonitor.evaluation.business.forms.CreateBusinessViewModel
import viewmodel.ViewModel
import pimonitor.evaluation.business.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.business.forms.CreateBusinessState as State

class AddBusinessScope(service: PiMonitorService) {
    val viewModel: ViewModel<Intent, State> by lazy { CreateBusinessViewModel(service.monitors, service.businesses) }
    val showForm: (id: String?) -> Unit = {
        viewModel.post(Intent.ShowForm(it))
    }
}