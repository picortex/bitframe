@file:JsExport

package bitframe.client.panel

import bitframe.core.Session
import kotlin.js.JsExport

sealed class PanelState {
    data class Loading(val message: String) : PanelState()

    data class Panel(val session: Session.SignedIn) : PanelState()

    object Login : PanelState()
}
