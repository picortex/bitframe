@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.businesses.exports

import bitframe.client.ReactUIScope
import bitframe.client.UIScope
import pimonitor.api.PiMonitorService
import pimonitor.evaluation.businesses.forms.CreateBusinessViewModel
import useViewModelState
import viewmodel.ViewModel
import pimonitor.evaluation.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.businesses.forms.CreateBusinessState as State

class CreateBusinessReactScope(
    service: PiMonitorService
) : CreateBusinessScope(service), ReactUIScope<Intent, State>