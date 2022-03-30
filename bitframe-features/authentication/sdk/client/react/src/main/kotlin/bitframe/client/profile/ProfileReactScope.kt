@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.profile

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import viewmodel.asState

@JsExport
class ProfileReactScope(
    override val config: UIScopeConfig<ProfileService>
) : ProfileScope(config), ReactUIScope<ProfileState> {
    override val useScopeState = { viewModel.asState() }
}