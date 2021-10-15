@file:JsExport

package bitframe.panel

import bitframe.authentication.signin.Session
import kotlin.js.JsExport
import kotlin.js.JsName

sealed class PanelState {
    data class Loading(val message: String) : PanelState()
    data class Panel(
        val session: Session,
        @JsName("moduleList")
        val modules: List<UIModuleGroup>
    ) : PanelState() {
        @JsName("modules")
        val moduleArray
            get() = modules.toTypedArray()
    }
}
