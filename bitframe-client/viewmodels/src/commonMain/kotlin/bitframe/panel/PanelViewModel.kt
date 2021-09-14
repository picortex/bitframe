@file:JsExport

package bitframe.panel

import kotlinx.coroutines.CoroutineScope
import viewmodel.ViewModel
import kotlin.js.JsExport
import bitframe.panel.PanelIntent as Intent
import bitframe.panel.PanelState as State

class PanelViewModel : ViewModel<Intent, State>(State.Loading("Setting up your workspace")) {
    override fun CoroutineScope.execute(i: Intent): Any {
        TODO("Not yet implemented")
    }
}