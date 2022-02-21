@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.password

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import bitframe.client.profile.ProfileService
import viewmodel.asState
import bitframe.client.password.ChangePasswordIntent as Intent
import bitframe.client.password.ChangePasswordState as State

class ChangePasswordReactScope(
    override val config: UIScopeConfig<ProfileService>
) : ChangePasswordScope(config), ReactUIScope<Intent, State> {
    override val useScopeState: () -> State = { viewModel.asState() }
}