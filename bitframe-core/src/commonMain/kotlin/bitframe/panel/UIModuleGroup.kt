@file:JsExport

package bitframe.panel

import kotlin.js.JsExport

data class UIModuleGroup(
    val name: String,
    val modules: List<UIModule>
)