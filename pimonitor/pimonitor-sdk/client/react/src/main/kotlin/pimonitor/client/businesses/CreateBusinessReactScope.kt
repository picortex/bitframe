@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.businesses

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import useViewModelState
import pimonitor.client.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.client.businesses.forms.CreateBusinessState as State

class CreateBusinessReactScope internal constructor(
    override val config: UIScopeConfig<PiMonitorApi>
) : CreateBusinessScope(config), ReactUIScope<Intent, State> {
    override val useScopeState: () -> State = {
        useViewModelState(viewModel)
    }
}