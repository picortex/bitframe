@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.panel

import bitframe.client.BitframeViewModelConfig
import viewmodel.ViewModel

class PanelScope(config: BitframeViewModelConfig) {
    val viewModel: ViewModel<PanelIntent, PanelState> by lazy {
        PanelViewModel(config)
    }
    val initPanel = { viewModel.post(PanelIntent.InitPanel) }
}