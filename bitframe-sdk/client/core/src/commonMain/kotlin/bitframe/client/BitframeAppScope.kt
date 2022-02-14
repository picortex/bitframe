@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.client.panel.PanelScope
import bitframe.client.signin.SignInScope
import kotlin.js.JsExport

interface BitframeAppScope : SessionAware {
    val config: BitframeScopeConfig
    val signIn: SignInScope
    val panel: PanelScope
}