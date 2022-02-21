@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.panel

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import bitframe.client.signin.SignInService
import viewmodel.ViewModel
import kotlin.js.JsExport
import bitframe.client.panel.PanelIntent as Intent
import bitframe.client.panel.PanelState as State

open class PanelScope(override val config: UIScopeConfig<SignInService>) : UIScope<Intent, State> {
    override val viewModel: ViewModel<Intent, State> by lazy {
        PanelViewModel(config)
    }
    val initPanel = { viewModel.post(Intent.InitPanel) }
}