@file:JsExport

package bitframe.panel

import kotlin.js.JsExport
import kotlin.js.JsName

sealed class PanelState {
    data class Loading(val message: String) : PanelState()
    data class Panel(
        @JsName("moduleList")
        val modules: List<UIModuleGroup>
    ) : PanelState() {
        @JsName("modules")
        val moduleArray
            get() = modules.toTypedArray()
    }
}
