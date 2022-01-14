@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe

import bitframe.authentication.signin.Session
import bitframe.authentication.signin.exports.SignInScope
import bitframe.client.BitframeViewModelConfig
import bitframe.panel.PanelScope

interface BitframeScope {
    val config: BitframeViewModelConfig
    val signIn: SignInScope
    val panel: PanelScope

    val bus get() = config.service.bus
    val cache get() = config.service.cache
    val userLiveSession get() = config.service.signIn.session
    val userSession get() = userLiveSession.value as? Session.SignedIn
    val currentUser get() = userSession?.user
    val currentSpace get() = userSession?.space
}