@file:JsExport

package bitframe.panel

import kotlin.js.JsExport

sealed class PanelState {
    data class Loading(val message: String) : PanelState()
}
