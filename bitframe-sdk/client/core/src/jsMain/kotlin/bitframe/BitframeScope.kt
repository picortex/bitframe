@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe

import bitframe.api.SessionAware
import bitframe.authentication.signin.exports.SignInScope
import bitframe.client.BitframeViewModelConfig
import bitframe.panel.PanelScope

interface BitframeScope : SessionAware {
    val config: BitframeViewModelConfig
    val signIn: SignInScope
    val panel: PanelScope
}