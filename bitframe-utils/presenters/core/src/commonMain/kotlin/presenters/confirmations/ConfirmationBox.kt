@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package presenters.confirmations

import koncurrent.Later
import live.Live
import presenters.actions.MutableSimpleAction
import kotlin.js.JsExport

interface ConfirmationBox {
    val heading: String
    val details: String

    val ui: Live<ConfirmationState>

    val cancelAction: MutableSimpleAction
    fun confirm(): Later<Unit>
}