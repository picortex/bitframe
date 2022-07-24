package presenters.collections

import kotlin.js.JsExport

@JsExport
interface Actionable {
    val actionManager: ActionManager
}