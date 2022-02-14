@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.client

import bitframe.client.signin.SignInReactScope

interface BitframeReactAppScope : BitframeAppScope {
    override val signIn: SignInReactScope
}