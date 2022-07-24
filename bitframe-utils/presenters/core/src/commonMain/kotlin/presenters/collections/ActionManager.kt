@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections

import kotlinx.collections.interoperable.List
import presenters.actions.GenericPendingAction
import presenters.actions.SimpleAction
import kotlin.js.JsExport

@JsExport
interface ActionManager {
    val actions: List<SimpleAction>
}