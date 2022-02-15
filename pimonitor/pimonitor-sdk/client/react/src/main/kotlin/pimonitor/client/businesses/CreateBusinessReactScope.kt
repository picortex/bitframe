@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import pimonitor.PiMonitorScopeConfig
import pimonitor.evaluation.businesses.CreateBusinessScope
import useViewModelState
import pimonitor.evaluation.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.businesses.forms.CreateBusinessState as State

class CreateBusinessReactScope internal constructor(
    config: PiMonitorScopeConfig
) : CreateBusinessScope(config), ReactUIScope<Intent, State> {
    override val useStateFromViewModel: () -> State = {
        useViewModelState(viewModel)
    }
}