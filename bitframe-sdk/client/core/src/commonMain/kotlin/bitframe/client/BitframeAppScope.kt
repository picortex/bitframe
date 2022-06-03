@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.client.panel.PanelIntents
import bitframe.client.panel.PanelState
import bitframe.client.signin.SignInIntents
import bitframe.client.signin.SignInState
import kotlin.js.JsExport

@JsExport
interface BitframeAppScope<A : BitframeApi> {
    val api: A
    val session: SessionAware
    val signIn: MicroScope<SignInIntents, SignInState>
    val panel: MicroScope<PanelIntents, PanelState>
}