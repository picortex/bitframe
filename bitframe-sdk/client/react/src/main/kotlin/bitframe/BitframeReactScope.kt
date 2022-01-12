@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe

import bitframe.authentication.signin.exports.SignInReactScope

interface BitframeReactScope : BitframeScope {
    override val signIn: SignInReactScope
}