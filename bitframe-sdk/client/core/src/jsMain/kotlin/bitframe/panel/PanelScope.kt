@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.panel

import bitframe.api.BitframeService
import bitframe.client.BitframeViewModelConfig
import bitframe.client.UIScope
import viewmodel.ViewModel

open class PanelScope(config: BitframeViewModelConfig) : UIScope<PanelIntent, PanelState> {
    override val service: BitframeService = config.service
    override val viewModel: ViewModel<PanelIntent, PanelState> by lazy {
        PanelViewModel(config)
    }
    val initPanel = { viewModel.post(PanelIntent.InitPanel) }
}