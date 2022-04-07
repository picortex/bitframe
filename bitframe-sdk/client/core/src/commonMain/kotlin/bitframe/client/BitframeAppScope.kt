@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.client.panel.PanelScope
import bitframe.client.signin.SignInScope
import kotlin.js.JsExport

@JsExport
interface BitframeAppScope<A : BitframeApi> {
    val session: SessionAware
    val signIn: SignInScope
    val panel: PanelScope
}