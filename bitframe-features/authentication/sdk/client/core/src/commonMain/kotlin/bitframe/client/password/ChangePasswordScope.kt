@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.password

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import bitframe.client.profile.ProfileService
import bitframe.core.profile.params.ChangePasswordParams
import bitframe.core.profile.params.RawChangePasswordParams
import kotlin.js.JsExport
import bitframe.client.password.ChangePasswordIntent as Intent
import bitframe.client.password.ChangePasswordState as State

@JsExport
class ChangePasswordScope(
    override val config: UIScopeConfig<ProfileService>
) : UIScope<Intent, State> {
    val changePassword = { params: RawChangePasswordParams ->
        viewModel.post(Intent(params))
    }
    override val viewModel by lazy { ChangePasswordViewModel(config) }
}