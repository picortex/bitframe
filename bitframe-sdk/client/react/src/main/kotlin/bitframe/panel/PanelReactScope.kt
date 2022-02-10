@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.panel

import bitframe.client.BitframeScopeConfig
import bitframe.client.ReactUIScope
import useViewModelState

class PanelReactScope(config: BitframeScopeConfig) : PanelScope(config), ReactUIScope<PanelIntent, PanelState> {
    override val useStateFromViewModel: () -> PanelState = {
        useViewModelState(viewModel)
    }
}