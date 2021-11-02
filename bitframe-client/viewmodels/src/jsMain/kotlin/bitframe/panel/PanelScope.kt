@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.panel

import bitframe.BitframeService
import viewmodel.ViewModel

class PanelScope(val service: BitframeService) {
    val viewModel: ViewModel<PanelIntent, PanelState> by lazy {
        PanelViewModel(service, service.cache)
    }
}