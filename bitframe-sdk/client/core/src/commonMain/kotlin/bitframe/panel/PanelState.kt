@file:JsExport

package bitframe.panel

import bitframe.service.client.Session
import kotlinx.collections.interoperable.List
import kotlin.js.JsExport

sealed class PanelState {
    data class Loading(val message: String) : PanelState()

    data class Panel(
        val session: Session.SignedIn,
        val modules: List<UIModuleGroup>
    ) : PanelState()

    object Login : PanelState()
}
