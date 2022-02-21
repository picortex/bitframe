@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.client.panel.PanelReactScope
import bitframe.client.signin.SignInReactScope

@JsExport
interface BitframeReactAppScope<A : BitframeApi> : BitframeAppScope<A> {
    override val signIn: SignInReactScope
    override val panel: PanelReactScope
}