@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.panel

import bitframe.api.BitframeService
import bitframe.client.UIScope
import viewmodel.ViewModel
import bitframe.panel.PanelIntent as Intent
import bitframe.panel.PanelState as State

class PanelScope(val service: BitframeService) : UIScope<Intent, State> {
    override val viewModel: ViewModel<Intent, State> by lazy {
        PanelViewModel(service, service.signIn.config.cache)
    }
    val initPanel = { viewModel.post(Intent.InitPanel) }
}