@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.profile

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import kotlin.js.JsExport
import bitframe.client.profile.ProfileIntent as Intent
import bitframe.client.profile.ProfileState as State

@JsExport
open class ProfileScope(
    override val config: UIScopeConfig<ProfileService>
) : UIScope<Intent, State> {
    override val viewModel by lazy { ProfileViewModel(config) }
    val initialize = {
        viewModel.post(Intent.Init)
    }
}