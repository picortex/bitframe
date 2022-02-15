@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.client.signin.SignInReactScope

@JsExport
interface BitframeReactAppScope : BitframeAppScope {
    override val signIn: SignInReactScope
}