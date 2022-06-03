package bitframe.client.panel

import bitframe.client.MicroScope
import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import bitframe.client.signin.SignInService
import viewmodel.ViewModel
import kotlin.js.JsExport
import bitframe.client.panel.PanelIntent as Intent
import bitframe.client.panel.PanelState as State

fun PanelScope(
    config: UIScopeConfig<SignInService>
) = MicroScope {
    viewModel(PanelViewModel(config))
    intents(PanelIntents(viewModel))
}