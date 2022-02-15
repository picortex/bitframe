@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.client.panel.PanelScope
import bitframe.client.signin.SignInScope
import kotlin.js.JsExport

interface BitframeAppScope {
    val config: BitframeAppScopeConfig
    val signIn: SignInScope
    val panel: PanelScope

    val session get() = config.api.session
}