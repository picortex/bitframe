@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe

import bitframe.authentication.signin.exports.SignInScope
import bitframe.panel.PanelScope

interface BitframeScope {
    val signIn: SignInScope
    val panel: PanelScope
}