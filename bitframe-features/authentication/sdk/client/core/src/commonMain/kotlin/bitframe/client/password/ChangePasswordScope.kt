@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.password

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import bitframe.client.profile.ProfileService
import bitframe.core.profile.params.ChangePasswordRawParams
import kotlin.js.JsExport
import bitframe.client.password.ChangePasswordIntent as Intent
import bitframe.client.password.ChangePasswordState as State

open class ChangePasswordScope(
    override val config: UIScopeConfig<ProfileService>
) : UIScope<Intent, State> {
    override val viewModel by lazy { ChangePasswordViewModel(config) }
    val changePassword = { params: ChangePasswordRawParams ->
        viewModel.post(Intent(params))
    }
}