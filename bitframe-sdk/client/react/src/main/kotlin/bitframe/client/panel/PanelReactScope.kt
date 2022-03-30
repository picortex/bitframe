@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.panel

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import bitframe.client.signin.SignInService
import useViewModelState
import viewmodel.asState

class PanelReactScope(
    override val config: UIScopeConfig<SignInService>
) : PanelScope(config), ReactUIScope<PanelState> {
    override val useScopeState = { viewModel.asState() }
}