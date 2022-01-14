@file:JsExport

package bitframe.panel

import bitframe.client.BitframeViewModelConfig
import bitframe.client.ReactUIScope
import useViewModelState

class PanelReactScope(config: BitframeViewModelConfig) : PanelScope(config), ReactUIScope<PanelIntent, PanelState> {
    override val useStateFromViewModel: () -> PanelState = {
        useViewModelState(viewModel)
    }
}