@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.panel

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import bitframe.client.signin.SignInService
import useViewModelState

class PanelReactScope(config: UIScopeConfig<SignInService>) : PanelScope(config), ReactUIScope<PanelIntent, PanelState> {
    override val useScopeState: () -> PanelState = {
        useViewModelState(viewModel)
    }
}