@file:JsExport

package bitframe.client.panel

import viewmodel.ViewModel
import kotlin.js.JsExport

class PanelIntents internal constructor(
    private val viewModel: ViewModel<PanelIntent, *>
) {
    val initializePanel = {
        viewModel.post(PanelIntent.InitPanel)
    }
}